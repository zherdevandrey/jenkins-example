package com.example.gcpsimpleexample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class JenkinsSimpleExampleApplication {

	@GetMapping("/")
	public String test() {
		System.out.println("dev");
		return "dev";
	}

    public static void main(String[] args) {
        SpringApplication.run(JenkinsSimpleExampleApplication.class, args);
    }

}
