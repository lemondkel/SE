package com.l2jong.se.impl;

import com.l2jong.se.dao.UsrDao;
import com.l2jong.se.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsrServiceImpl implements UsrService {

	@Autowired
	private UsrDao usrDao;

	@Override
	public int getCount() {
		return usrDao.getCount();
	}
}
