package com.l2jong.se.impl;

import com.l2jong.se.dao.BrdDao;
import com.l2jong.se.dao.UsrDao;
import com.l2jong.se.service.BrdService;
import com.l2jong.se.service.UsrService;
import com.l2jong.se.vo.BrdVO;
import com.l2jong.se.vo.UsrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrdServiceImpl implements BrdService {

	@Autowired
	private BrdDao brdDao;

	@Override
	public List<BrdVO> selectBrdByPaging(BrdVO brdVO) {
		return brdDao.selectBrdByPaging(brdVO);
	}

	@Override
	public int insertBrd(BrdVO brdVO) {
		return brdDao.insertBrd(brdVO);
	}
}
