package com.mike.cloud.learningmqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mike.cloud.learningmqtt.config.CustomProcess.Person;

@Controller
@SpringBootApplication
public class MqttLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttLearningApplication.class, args);
	}
	

    @Autowired
    private StreamBridge  resolver;

    @RequestMapping(value="/{target}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void send(@RequestBody Person body, @PathVariable("target") String target){
		resolver.send(target, body);
	}
	
	
}
