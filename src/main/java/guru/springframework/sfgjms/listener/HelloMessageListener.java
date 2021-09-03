package guru.springframework.sfgjms.listener;


import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jsJmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message){
//        System.out.println("I Got a Message!!!!!");
//
//        System.out.println(helloWorldMessage);


    }

    @JmsListener(destination = JmsConfig.MY_SEND_REC_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message) throws JMSException {
//        System.out.println("I Got a Message!!!!!");
//
//        System.out.println(helloWorldMessage);


        HelloWorldMessage payloadMessage = HelloWorldMessage.builder().id(UUID.randomUUID()).message("World!!!")
                .build();

        jsJmsTemplate.convertAndSend((Destination) message.getJMSReplyTo(), payloadMessage);

    }
}
