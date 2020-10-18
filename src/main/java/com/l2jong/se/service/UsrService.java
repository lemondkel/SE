package com.l2jong.se.service;

import com.l2jong.se.vo.UsrVO;

import java.util.List;

public interface UsrService {
	public int getCount();

	int insertUsr(UsrVO usrVO);

	List<UsrVO> selectUsrByPaging(UsrVO usrVO);

	boolean isExistUser(String userId);

	String userLogin(String userPassword);

	UsrVO getUserInformation(String userId);
}
