package email.service.handler;
import dto.EmailMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class emailHandler {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine engine;

    @RabbitListener(queues = "mail-queue")
    public void  sendMail(EmailMessageDTO message){
        try {
            MimeMessage mimeMessage = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

            helper.setSubject("注册邮件");

            //通过模板引擎，将模板+数据得到邮件信息
            Context context = new Context();
            context.setVariable("username",message.getEmail().substring(0,message.getEmail().lastIndexOf('@')));
            context.setVariable("url",message.getUrl());
            String info = engine.process("emailtemplate", context);


            helper.setText(info,true);
            helper.setFrom("xushicai0210@163.com");
            helper.setTo(message.getEmail());

            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
