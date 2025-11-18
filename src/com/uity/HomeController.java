package com.uity;


import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;




@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		 ModelAndView mav = new ModelAndView ("input");
		return mav;
	
	}
	

	@RequestMapping(value = "/output", method = RequestMethod.POST)
	public String printHello(ModelMap model) 
	{
		return "output"; 
	}
	
	@RequestMapping(value = "/evaluate", method = RequestMethod.POST)
	public String printrsq(ModelMap model) 
	{
		return "evaluate"; 
	}
}

