package com.example.login.service;

import com.example.login.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailService implements Runnable {
    User user;

    public EmailService() {
    }

    public EmailService(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // qq邮箱服务器
        prop.setProperty("mail.transport.protocol", "smtp");   // smtp协议
        prop.setProperty("mail.smtp.auth", "true");   // 设置需要验证码

        try {
            // SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl,enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            // 创建Session
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2702461713@qq.com", "phmnapepcubkdfgf");
                }
            });
            session.setDebug(true);

            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com", "2702461713@qq.com", "phmnapepcubkdfgf");
            MimeMessage mimeMessage = new MimeMessage(session);
            // 发件人
            mimeMessage.setFrom(new InternetAddress("2702461713@qq.com"));
            // 收件人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            mimeMessage.setSubject("Welcome to FileUploadProject");
            mimeMessage.setContent("欢迎登录", "text/html;charset=utf-8");

            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (GeneralSecurityException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
