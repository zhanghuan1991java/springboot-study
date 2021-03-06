package com.didispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
//@MapperScan({"com.didispace.cache.ehcache","com.didispace.mybatis.a_user"})
//@EnableCaching
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}
}


