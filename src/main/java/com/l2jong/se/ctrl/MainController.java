package com.l2jong.se.ctrl;

import com.l2jong.se.service.UsrService;
import com.l2jong.se.util.AjaxResult;
import com.l2jong.se.util.ComnUtil;
import com.l2jong.se.vo.UsrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	private UsrService usrService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public MainController() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@RequestMapping("/")
	public ModelAndView main(HttpSession session) {
		ModelAndView mav = new ModelAndView("main");

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

	@ResponseBody
	@RequestMapping(value = "/process/login", method = RequestMethod.POST)
	public AjaxResult loginProcess(@ModelAttribute UsrVO usrVO, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			if (usrService.isExistUser(usrVO.getUserId())) {
				// User Found
				if (bCryptPasswordEncoder.matches(usrVO.getUserPassword(), usrService.userLogin(usrVO.getUserId()))) { // 아이디, 패스워드 체크
					// Success
					UsrVO userDetail = usrService.getUserInformation(usrVO.getUserId());
					session.setAttribute("se_login_user", userDetail);
					result.setCode(0);
					result.setResult(true);
					result.setMessage("Success");
				} else {
					// Uncorrect Password
					result.setCode(1);
					result.setResult(false);
					result.setMessage("Password is incorrect");
				}
			} else {
				// Not found user
				result.setCode(1);
				result.setResult(false);
				result.setMessage("User is not found");
			}
		} catch (IllegalArgumentException e) {
			/**
			 * Uncorrect Password
			 */
			e.printStackTrace();
			result.setCode(1);
			result.setResult(false);
			result.setMessage("Password is incorrect");
		} catch (Exception e) {
			// Other Server Error
			e.printStackTrace();
			result.setCode(999);
			result.setResult(false);
			result.setMessage("Server error");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/process/join", method = RequestMethod.POST)
	public AjaxResult joinProcess(@ModelAttribute UsrVO usrVO, HttpSession session) {
		AjaxResult result = new AjaxResult();

		if (ComnUtil.isEmptyOrNull(usrVO.getUserId())) {
			result.setCode(2);
			result.setResult(false);
			result.setMessage("Put your field - ID");
			return result;
		}

		if (ComnUtil.isEmptyOrNull(usrVO.getUserPassword())) {
			result.setCode(2);
			result.setResult(false);
			result.setMessage("Put your field - Password");
			return result;
		}

		if (ComnUtil.isEmptyOrNull(usrVO.getNickname())) {
			result.setCode(2);
			result.setResult(false);
			result.setMessage("Put your field - Nickname");
			return result;
		}

		try {
			if (usrService.isExistUser(usrVO.getUserId())) {
				// User Found
				result.setCode(1);
				result.setResult(false);
				result.setMessage("Already joined user id");
			} else {
				// Success
				usrVO.setUserPassword(bCryptPasswordEncoder.encode(usrVO.getUserPassword()));
				int resultCount = usrService.insertUsr(usrVO);
				if (resultCount > 0) {
					UsrVO userDetail = usrService.getUserInformation(usrVO.getUserId());
					session.setAttribute("se_login_user", userDetail);
					result.setCode(0);
					result.setResult(true);
					result.setMessage("Success");
				} else {
					result.setCode(999);
					result.setResult(false);
					result.setMessage("Server error");
				}
			}
		} catch (Exception e) {
			// Other Server Error
			e.printStackTrace();
			result.setCode(999);
			result.setResult(false);
			result.setMessage("Server error");
		}
		return result;
	}
}
