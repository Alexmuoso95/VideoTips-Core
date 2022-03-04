package com.videotips.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class VideoTipsCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoTipsCoreApplication.class, args);
	}

}
