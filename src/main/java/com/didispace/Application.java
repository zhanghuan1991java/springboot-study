package com.didispace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@MapperScan({"com.didispace.mybatis.employees.mapper","com.didispace.cache.ehcache"})
@EnableCaching
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


