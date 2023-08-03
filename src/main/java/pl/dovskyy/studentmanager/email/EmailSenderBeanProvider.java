package pl.dovskyy.studentmanager.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailSenderBeanProvider {

    @Bean
    public JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }

}
