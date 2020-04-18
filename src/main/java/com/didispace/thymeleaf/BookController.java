package com.didispace.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	
    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public ModelAndView books() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setId(1);
        b1.setAuthor("罗贯中");
        b1.setName("三国演义");
        Book b2 = new Book();
        b2.setId(2);
        b2.setAuthor("曹雪芹");
        b2.setName("红楼梦");
        books.add(b1);
        books.add(b2);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.addObject("name", "许三多");
        mv.setViewName("books");
        return mv;
    }
    
    
    @RequestMapping("/name1")
    public String name(ModelMap map) {
    	
    	map.addAttribute("userrole","001");
    	
    	Book b1 = new Book();
        b1.setId(1);
        b1.setAuthor("罗贯中");
        b1.setName("三国演义");
        map.addAttribute("books", b1);
        
    	return "name1";
    	
    }
    
    
    
}