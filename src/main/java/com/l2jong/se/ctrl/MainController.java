package com.l2jong.se.ctrl;

import com.l2jong.se.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	private UsrService usrService;

	@RequestMapping("/")
	public String main() {
		int a = usrService.getCount();
		System.out.println(a);
		return "main";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/join")
	public String join() {
		return "join";
	}
}
