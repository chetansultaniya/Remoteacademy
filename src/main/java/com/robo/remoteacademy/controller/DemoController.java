package com.robo.remoteacademy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/remoteacademy/")
public class DemoController {

	@RequestMapping(value = "/show/{pageName}", method = RequestMethod.GET)
	public ModelAndView showPage(@PathVariable("pageName") String pageName,HttpServletRequest request) 
	{
		ModelAndView mv = new ModelAndView(pageName);
		mv.addObject("title", pageName);
		return mv;
	}    
	
}
