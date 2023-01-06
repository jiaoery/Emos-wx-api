package com.jiaoery.emos.wx;

import com.jiaoery.emos.wx.common.util.R;
import com.jiaoery.emos.wx.controller.TestController;
import com.jiaoery.emos.wx.controller.form.TestSayHelloForm;
import com.jiaoery.emos.wx.db.pojo.TbUser;
import com.jiaoery.emos.wx.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: UserServiceTest
 * Description:UserServiceTest
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/9/9 18:32
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private TestController testController;

    @Test
    public void testSearchById(){
        System.out.println("testSearchById");
        TestSayHelloForm testSayHelloForm =new TestSayHelloForm();
        R r = testController.sayHello(testSayHelloForm);
        Assertions.assertThat(r);
        System.out.println("code:"+r.get("code")+";message:"+r.get("msg"));
    }
}
