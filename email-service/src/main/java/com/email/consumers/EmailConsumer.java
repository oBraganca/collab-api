package com.email.consumers;

import com.email.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private static final String TOPIC = "twitterclone-new-register";
    private static final String GROUP = "twitterclone";
    private final EmailService emailService;
    private static final Logger log = LoggerFactory.getLogger(EmailConsumer.class);

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void receiveEmail(String email) throws Exception {
        log.info("Consumer: e-mail recebido: {} ", email);
        try{
            emailService.sendEmail(email);
        }catch (Exception e){
            log.error("Erro ao enviar código", e);
        }
    }
}
