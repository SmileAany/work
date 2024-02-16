package com.smile.work.imap.service.impl;

import com.smile.work.imap.service.ImapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/2/3 12:11
 */
@Service
@Slf4j
public class ImapServiceImpl implements ImapService {
    @Override
    public void readEmails(String username,String password) {
        if (StringUtils.isAnyBlank(username,password)) {
            log.error("参数异常");

            return;
        }

        Properties properties = new Properties();

        properties.setProperty("mail.store.protocol", "pop3");
        properties.setProperty("mail.pop3.host", "pop.gmail.com");
        properties.setProperty("mail.pop3.port", "995");
        properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");

        try {
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("pop3s");
            store.connect("pop.gmail.com", username, password);

            if (store.isConnected()) {
                log.info("gmail 链接成功");
            } else {
                log.error("gmail 链接失败");

                return;
            }

            for (Folder personalNamespace : store.getPersonalNamespaces()) {
                log.info(personalNamespace.getName());
            }

            Folder inbox = store.getFolder("INBOX");

            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                // 处理邮件内容，你可以根据需求进行其他操作
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Date: " + message.getSentDate());
            }

            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
