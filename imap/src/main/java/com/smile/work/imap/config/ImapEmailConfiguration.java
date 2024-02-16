//package com.smile.work.imap.config;
//
//import com.sun.tools.javac.code.Flags;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import javax.mail.Folder;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.NoSuchProviderException;
//import javax.mail.Session;
//import javax.mail.Store;
//import javax.mail.search.FlagTerm;
//import java.sql.SQLException;
//import java.util.Properties;
//
///**
// * @author: smile
// * @title:
// * @projectName: sso
// * @description: TODO
// * @date: 2024/2/3 12:18
// */
//@Configuration
//public class ImapEmailConfiguration {
//    @Value("${spring.mail.username}")
//    private String username;
//
//    // 配置线程池执行器
//    @Bean
//    public ThreadPoolTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        // 根据实际情况配置核心线程数、最大线程数等参数
//        executor.setCorePoolSize(5);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(20);
//        executor.initialize();
//        return executor;
//    }
//
//    public void fetchAndProcessEmails(ThreadPoolTaskExecutor executor) throws MessagingException, InterruptedException, NoSuchProviderException {
//        Properties props = System.getProperties();
//        props.setProperty("mail.store.protocol", "imaps");
//        Session session = Session.getDefaultInstance(props, null);
//
//        Store store = session.getStore("imaps");
//        store.connect(username, "ad");
//
//        Folder inbox = store.getFolder("INBOX");
//        inbox.open(Folder.READ_ONLY);
//
//        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.UNSEEN), true));
//
//        for (final Message message : messages) {
//            executor.execute(() -> {
//                try {
//                    processMessage(message); // 处理单个邮件的方法
//                } catch (MessagingException | SQLException e) {
//                    // 异常处理
//                }
//            });
//        }
//
//        inbox.close(true);
//        store.close();
//    }
//
////    private void processMessage(Message message) throws MessagingException, SQLException {
////        // 解析邮件内容，例如：subject、from、to、body等
////        // 然后将数据保存到数据库中
////        // 假设有一个EmailEntity实体和对应的EmailRepository
////        EmailEntity emailEntity = mapToEmailEntity(message);
////        emailRepository.save(emailEntity);
////    }
////
////    // 实现从Message对象到EmailEntity的映射方法
////    private EmailEntity mapToEmailEntity(Message message) {
////        // ... 这里是具体的映射逻辑，根据你的数据库模型转换邮件内
////
////    }
//}
