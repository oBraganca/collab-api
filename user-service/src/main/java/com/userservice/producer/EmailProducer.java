package com.userservice.producer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmailProducer {

    private static final String NEW_USER_TOPIC = "new-user-register";
    private static final Integer PAUSE_TIME = 15000;
    private static final Integer LIMIT_TIME = 300;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(EmailProducer.class);


    public EmailProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageEmailConfirmation (String email)  throws Exception {
        boolean notSent = true;
        int waitingTime = 0;

        do {
            try {
                kafkaTemplate.send(NEW_USER_TOPIC, email);
                notSent = false;
                log.error("Mensagem enviada com SUCESSO para o tópico: {}", NEW_USER_TOPIC);
            } catch (Exception e) {
                log.error("Erro ao enviar mensagem para o tópico: {}", NEW_USER_TOPIC);
                Thread.sleep(PAUSE_TIME);
                waitingTime += 15;
            }
        } while (notSent && waitingTime <= LIMIT_TIME);
    }
}