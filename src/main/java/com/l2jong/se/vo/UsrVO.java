package com.l2jong.se.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class UsrVO {

	private int no;
	private String userId;
	private String nickname;
	private Date createdAt;

	private int pageNo;
	private int startRowNum;
	private int endRowNum;
}
