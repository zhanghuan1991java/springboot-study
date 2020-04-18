package com.didispace.properties;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {
	
	@Autowired
	private Person person;
	
	@RequestMapping(value="/person",method=RequestMethod.GET)
	public Person getPerson() {
		
		Person bean = new Person();
		
		BeanUtils.copyProperties(person, bean);
		
		return bean;
	}
	
}
