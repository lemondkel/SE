package com.l2jong.se.impl;

import com.l2jong.se.dao.UsrDao;
import com.l2jong.se.service.UsrService;
import com.l2jong.se.vo.UsrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsrServiceImpl implements UsrService {

	@Autowired
	private UsrDao usrDao;

	@Override
	public int getCount() {
		return usrDao.getCount();
	}

	@Override
	public int insertUsr(UsrVO usrVO) {
		return usrDao.insertUsr(usrVO);
	}

	@Override
	public List<UsrVO> selectUsrByPaging(UsrVO usrVO) {
		return usrDao.selectUsrByPaging(usrVO);
	}

	@Override
	public boolean isExistUser(String userId) {
		return usrDao.isExistUser(userId);
	}

	@Override
	public String userLogin(String userPassword) {
		return usrDao.userLogin(userPassword);
	}

	@Override
	public UsrVO getUserInformation(String userId) {
		return usrDao.getUserInformation(userId);
	}
}
