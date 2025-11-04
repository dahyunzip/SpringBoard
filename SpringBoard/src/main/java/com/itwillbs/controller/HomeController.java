package com.itwillbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// http://localhost:8088/controller/ (x)
	// http://localhost:8088/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//model.addAttribute("serverTime", formattedDate );
		
		//return "home";
		//return "redirect:/board/listALL";
		return "redirect:/board/listCri";
	}
	
	// REST동작 실행시작점
	// http://localhost:8088/rest
	@RequestMapping(value = "/rest",method = RequestMethod.GET)
	public String restHome() throws Exception{
		logger.info(" /rest -> restHome() 실행 ");
		logger.info(" REST 동작을 수행! ");
		
		return "/restStart";
	}
	
	
	
	
	
	
}
