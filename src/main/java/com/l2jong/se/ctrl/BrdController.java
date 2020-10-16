package com.l2jong.se.ctrl;

import com.l2jong.se.service.BrdService;
import com.l2jong.se.util.AjaxResult;
import com.l2jong.se.util.ComnUtil;
import com.l2jong.se.vo.BrdVO;
import com.l2jong.se.vo.UsrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/brd")
public class BrdController {

	@Autowired
	private BrdService brdService;

	@RequestMapping(value = "/get/{pageNo}", method = RequestMethod.GET)
	public AjaxResult getBrd(@PathVariable("pageNo") int pageNo) {
		AjaxResult ajaxResult = new AjaxResult();

		BrdVO brdVO = new BrdVO();
		brdVO.setStartRowNum(1 + ((pageNo - 1) * 10));
		brdVO.setEndRowNum(10 + ((pageNo - 1) * 10));
		try {
			List<BrdVO> selectBrdByPaging = brdService.selectBrdByPaging(brdVO);
			if (selectBrdByPaging.size() > 0) {
				ajaxResult = new AjaxResult(true, 0, "Success");
				ajaxResult.getData().put("selectBrdByPaging", selectBrdByPaging);
			} else {
				ajaxResult = new AjaxResult(false, 1, "Data is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false, 999, "Server error");
		}
		return ajaxResult;
	}

	@RequestMapping(value = "/put", method = RequestMethod.PUT)
	public AjaxResult selectBrdByPaging(@RequestBody BrdVO brdVO, HttpSession session) {
		AjaxResult ajaxResult;

		UsrVO sessionUser = (UsrVO) session.getAttribute("se_login_user");
		if (ComnUtil.isNull(sessionUser)) {
			ajaxResult = new AjaxResult(false, 2, "Not allowed permission");
			return ajaxResult;
		}

		brdVO.setWriteUserNo(sessionUser.getNo());
		int resultCount = brdService.insertBrd(brdVO);

		try {
			if (resultCount > 0) {
				ajaxResult = new AjaxResult(true, 0, "Success");
			} else {
				ajaxResult = new AjaxResult(false, 1, "Data is not comitted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false, 999, "Server error");
		}
		return ajaxResult;
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
