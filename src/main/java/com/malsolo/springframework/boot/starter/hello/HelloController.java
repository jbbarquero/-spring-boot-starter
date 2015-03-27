package com.malsolo.springframework.boot.starter.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	HelloProperties props;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		return "Hello " + name;
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam String name) {
		return props.getGreeting() + name;
	}
	
}
