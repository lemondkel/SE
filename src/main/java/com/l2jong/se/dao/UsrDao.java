package com.l2jong.se.dao;

import com.l2jong.se.vo.UsrVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsrDao {
	int getCount();

	int insertUsr(UsrVO usrVO);

	List<UsrVO> selectUsrByPaging(UsrVO usrVO);
}
