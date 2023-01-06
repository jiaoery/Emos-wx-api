package com.jiaoery.emos.wx.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.jiaoery.emos.wx.config.SystemConstants;
import com.jiaoery.emos.wx.db.dao.*;
import com.jiaoery.emos.wx.db.pojo.TbCheckin;
import com.jiaoery.emos.wx.exception.EmosException;
import com.jiaoery.emos.wx.service.CheckinService;
import com.jiaoery.emos.wx.task.EmailTask;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * ClassName: CheckinServiceImpl
 * Description:CheckinServiceImpl
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/7 14:28
 */
@Service
@Scope("prototype")
@Slf4j
public class CheckinServiceImpl implements CheckinService {
    @Autowired
    private SystemConstants systemConstants;

    @Autowired
    private TbHolidaysDao holidaysDao;

    @Autowired
    private TbWorkdayDao workdayDao;

    @Autowired
    private TbCheckinDao checkinDao;

    @Autowired
    private TbFaceModelDao faceModelDao;

    @Autowired
    private TbCityDao cityDao;

    @Autowired
    private TbUserDao userDao;

    @Value("${emos.face.checkinUrl}")
    private String checkinUrl;

    @Value("${emos.email.hr}")
    private String hrEmail;

    @Autowired
    private EmailTask emailTask;

    @Override
    public String validCanChecKin(int userId, String date) {
        boolean bool_1 = holidaysDao.searchTodayIsHoliday() != null ? true : false;
        boolean bool_2 = workdayDao.searchTodayIsWorkday() != null ? true : false;
        String type = "工作日";
        if (DateUtil.date().isWeekend()) {
            type = "节假日";
        }
        if (bool_1) {
            type = "节假日";
        } else if (bool_2) {
            type = "工作日";
        }
        if (type.equals("节假日")) {
            return "节假日不需要考勤";
        } else {
            DateTime now = DateUtil.date();
            String start = DateUtil.today() + " " + systemConstants.attendanceStartTime;
            String end = DateUtil.today() + " " + systemConstants.attendanceEndTime;
            DateTime attendanceStart = DateUtil.parse(start);
            DateTime attendanceEnd = DateUtil.parse(end);
            if (now.isBefore(attendanceStart)) {
                return "没有到上班考勤开始时间";
            } else if (now.after(attendanceEnd)) {
                return "超过了上班考勤结束时间";
            } else {
                HashMap map = new HashMap();
                map.put("userId", userId);
                map.put("date", date);
                map.put("start", start);
                map.put("end", end);
                boolean bool = checkinDao.haveCheckin(map) != null ? true : false;
                return bool ? "今日已经考勤,不用重复考勤" : "可以考勤";
            }
        }
    }

    @Override
    public void checkin(HashMap param) {
        //判断签到
        Date d1 = DateUtil.date();//当时时间
        Date d2 = DateUtil.parse(DateUtil.today() + " " + systemConstants.attendanceStartTime);
        Date d3 = DateUtil.parse(DateUtil.today() + "" + systemConstants.attendanceEndTime);

        int status = 0;
        if (d1.compareTo(d2) <= 0) {
            status = 1;//正常签到
        } else if (d1.compareTo(d2) > 0 && d1.compareTo(d3) < 0) {
            status = 2;//迟到
        }
        int userId = (Integer) param.get("userId");
        String faceModel = faceModelDao.searchFaceModel(userId);
        if (faceModel == null) {
            throw new RuntimeException("不存在人脸模型");
        } else {
            String path = (String) param.get("path");
            HttpRequest httpRequest = HttpUtil.createPost(checkinUrl);
            httpRequest.form("photo", FileUtil.file(path), "targetModel", faceModel);
            HttpResponse httpResponse = httpRequest.execute();
            if (httpResponse.getStatus() != 200) {
                log.error("人脸识别服务异常");
                throw new RuntimeException("人脸识别服务异常");
            }
            String body = httpResponse.body();
            if ("无法识别出人脸".equals(body) || "照片中存在多张人脸".equals(body)) {
                throw new RuntimeException(body);
            } else if ("False".equals(body)) {
                throw new RuntimeException("签到无效,非本人签到");
            } else if ("True".equals(body)) {
                // 这里需要获取到签到地区新冠疫情风险等级
                int risk = 1;
                String city = (String) param.get("city");
                String district = (String) param.get("district");
                if (!StrUtil.isBlank(city) && !StrUtil.isBlank(district)) {
                    String code = cityDao.searchCode(city);
                    try {
                        String url = "http://m." + code + ".bendibao.com/news/yqdengji/?qu=" + district;
                        Document document = Jsoup.connect(url).get();
                        Elements elements = document.getElementsByClass("list-content");
                        if (elements.size() > 0) {
                            Element element = elements.get(0);
                            String result = element.select("p:last-child").text();
                            if ("高风险".equals(result)) {
                                risk = 3;
                                //发送告警邮件
                                HashMap<String,String> hashMap = userDao.searchNameAndDept(userId);
                                String name = hashMap.get("name");
                                String deptName  = hashMap.get("dept_name");
                                deptName= deptName!=null?deptName:"";
                                SimpleMailMessage message = new SimpleMailMessage();
                                message.setTo(hrEmail);
                                message.setSubject("员工"+name+"身处高疫情地区警告");
                                message.setText(deptName+"员工"+name+","+DateUtil.format(new Date(),"yyyy年MM月dd日"));

                            } else if ("中风险".equals(result)) {
                                risk = 2;
                            }
                        }
                    } catch (Exception e) {
                        log.error("执行异常",e);
                        throw new EmosException("获取风险等级失败");
                    }
                }
                // 保存签到记录
                String address = (String) param.get("address");
                String country = (String) param.get("country");
                String province = (String) param.get("province");
                TbCheckin entity = new TbCheckin();
                entity.setUserId(userId);
                entity.setAddress(address);
                entity.setCountry(country);
                entity.setProvince(province);
                entity.setCity(city);
                entity.setDistrict(district);
                entity.setStatus((short) status);
                entity.setDate(DateUtil.today());
                checkinDao.insert(entity);
            }
        }
    }
}
