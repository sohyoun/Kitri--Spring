package com.kitri.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.hello.model.service.HelloService;

@Controller
public class HelloController {
	
	private HelloService helloService;
	
	
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}

	@RequestMapping("/hello.kitri")
	public ModelAndView greeting() {
		ModelAndView mav = new ModelAndView();
		String msg = helloService.hello("박소현");
		mav.addObject("msg", msg);
		mav.setViewName("result"); //result만 하면 앞 뒤에 알아서 hello/result.jsp로 붙여줌
		return mav;
	}
}
