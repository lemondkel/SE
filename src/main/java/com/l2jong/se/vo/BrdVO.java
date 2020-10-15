package com.l2jong.se.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BrdVO {

	private int no;
	private char viewType;
	private String title;
	private String content;
	private char delYn;
	private String writeUserId;
}
