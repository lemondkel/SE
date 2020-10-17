package com.l2jong.se.ctrl;

import com.l2jong.se.vo.UsrVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView main(HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		UsrVO usrVO = new UsrVO();
		usrVO.setNickname("22jong");
		usrVO.setUserId("l2jong");
		usrVO.setNo(1);
		session.setAttribute("se_login_user", usrVO);

		return mav;
	}

	@RequestMapping("/search/{searchText}/{pageNo}")
	public ModelAndView search(HttpSession session,
							   @PathVariable("searchText") String searchText,
							   @PathVariable("pageNo") int pageNo) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("searchText", searchText);
		mav.addObject("pageNo", pageNo);
		return mav;
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
