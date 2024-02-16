package com.smile.work.imap;

import com.smile.work.imap.service.ImapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/2/2 18:18
 */
@SpringBootApplication
public class ImapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImapApplication.class,args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ImapService emailService) {
        return args -> {
            // 指定Gmail邮箱的相关信息
            String username = "smileandywj@gmail.com";
            String password = "pqjc pplg nrdx movm";

            // 调用读取邮件的方法
            emailService.readEmails(username, password);
        };
    }
}
