package com.l2jong.se.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class BrdVO {

	private int no;
	private char viewType;
	private String content;
	private char delYn;
	private int writeUserNo;
	private String createdAt;

	private int pageNo;
	private int startRowNum;
	private int endRowNum;

	private String userId;
	private String nickname;
}
