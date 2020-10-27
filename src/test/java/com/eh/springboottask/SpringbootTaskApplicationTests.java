package com.eh.springboottask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootTaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 测试简单邮件发送
     */
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");

        message.setTo("892084947@qq.com");
        // 需要保证登录人邮箱跟发信人邮箱一致，否则会报：553 Mail from must equal authorized user
        message.setFrom("lienhui1992@yeah.net");

        mailSender.send(message);
    }

    /**
     * 测试复杂邮件发送
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        // 设置样式
        helper.setText("<b style='color:red'>今天 7:30 开会</b>", true);

        helper.setTo("892084947@qq.com");
        helper.setFrom("lienhui1992@yeah.net");

        //添加附件
        helper.addAttachment("1.jpg", new File("/Users/david/my/picture/1.jpg"));
        helper.addAttachment("2.jpg", new File("/Users/david/my/picture/2.jpg"));

        mailSender.send(mimeMessage);

    }


}
