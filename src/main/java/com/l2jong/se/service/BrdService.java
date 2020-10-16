package com.l2jong.se.service;

import com.l2jong.se.vo.BrdVO;

import java.util.List;

public interface BrdService {
	List<BrdVO> selectBrdByPaging(BrdVO brdVO);

	int insertBrd(BrdVO brdVO);
}
