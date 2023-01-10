package com.jiaoery.emos.wx.service;

import com.jiaoery.emos.wx.config.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * ClassName: CheckinService
 * Description:CheckinService
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/7 14:28
 */
public interface CheckinService {
    public String validCanChecKin(int userId,String date);

    public void checkin(HashMap param);

    public void createFaceModel(int userId,String path);
}
