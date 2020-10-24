package com.mike.configclient.config;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.mike.configclient.entity.Person;


@EnableBinding(Processor.class)
@EnableAutoConfiguration
public class CustomProcess {

	@Bean
	public Consumer<Person> log() {
	    return person -> {
	        System.out.println("Received: " + person);
	    };
	}
	
	 @Bean
		public Function<String, Message<String>> destinationAsPayload() {
			return value -> {
				return MessageBuilder.withPayload(value)
					.setHeader("spring.cloud.stream.sendto.destination", value).build();};
		}
	
	@Bean
	public Function<String, String> uppercase() {
		return value -> value.toUpperCase();
	}

//	@StreamListener(Sink.INPUT)
//    public void listen(@Payload String in, @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) {
//        System.out.println(in + " received from queue " + queue);
//    }
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
    public Person listen(@Payload Person in, @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) {
       System.out.println("Hello " + in.getName());
       in.setName(in.getName().toUpperCase());
	return in;
       
    }
}
