package com.l2jong.se.ctrl;

import com.l2jong.se.service.BrdService;
import com.l2jong.se.util.AjaxResult;
import com.l2jong.se.util.ComnUtil;
import com.l2jong.se.vo.BrdVO;
import com.l2jong.se.vo.UsrVO;
import de.svenjacobs.loremipsum.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/brd")
public class BrdController {

	private LoremIpsum loremIpsum;

	public BrdController() {
		this.loremIpsum = new LoremIpsum();
	}

	@Autowired
	private BrdService brdService;

	@RequestMapping(value = "/get/{pageNo}", method = RequestMethod.GET)
	public AjaxResult getBrd(@PathVariable("pageNo") int pageNo) {
		AjaxResult ajaxResult;

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
	public AjaxResult putBrd(@ModelAttribute BrdVO brdVO, HttpSession session) {
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
				brdVO.setUserId(sessionUser.getUserId());
				brdVO.setNickname(sessionUser.getNickname());
				ajaxResult.getData().put("targetPost", brdVO);
			} else {
				ajaxResult = new AjaxResult(false, 1, "Data is not comitted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false, 999, "Server error");
		}
		return ajaxResult;
	}

	@RequestMapping("/get/{searchText}/{pageNo}")
	public AjaxResult searchByText(HttpSession session,
								   @PathVariable("searchText") String searchText,
								   @PathVariable("pageNo") int pageNo) {
		AjaxResult ajaxResult;

		BrdVO brdVO = new BrdVO();
		brdVO.setStartRowNum(1 + ((pageNo - 1) * 10));
		brdVO.setEndRowNum(10 + ((pageNo - 1) * 10));
		brdVO.setContent(searchText);
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

	@RequestMapping(value = "/dummyDataGet", method = RequestMethod.GET)
	public AjaxResult dummyDataGet(HttpSession session) {
		AjaxResult ajaxResult;

		UsrVO sessionUser = (UsrVO) session.getAttribute("se_login_user");
		if (ComnUtil.isNull(sessionUser)) {
			ajaxResult = new AjaxResult(false, 2, "Not allowed permission");
			return ajaxResult;
		}

		BrdVO brdVO = new BrdVO();
		brdVO.setWriteUserNo(sessionUser.getNo());
		int resultCount = 0;
		for (int i = 0; i < 1000000; i++) {
			brdVO.setContent(loremIpsum.getParagraphs());
			System.out.println("PUT" + (i + 1));
			resultCount = brdService.insertBrd(brdVO);
		}

		try {
			if (resultCount > 0) {
				ajaxResult = new AjaxResult(true, 0, "Success");
				ajaxResult.getData().put("resultCount", resultCount);
			} else {
				ajaxResult = new AjaxResult(false, 1, "Data is not comitted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false, 999, "Server error");
		}
		return ajaxResult;
	}
}
