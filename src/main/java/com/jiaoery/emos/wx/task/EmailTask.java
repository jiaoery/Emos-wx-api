package com.jiaoery.emos.wx.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * ClassName: EmailTask
 * Description:EmailTask
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2023/1/6 16:00
 */
@Component
@Scope("prototype")
public class EmailTask implements Serializable {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${emos.email.system}")
    private String mailBox;

    @Async
    public void sendAsync(SimpleMailMessage message){
        message.setFrom(mailBox);
        javaMailSender.send(message);
    }
}
