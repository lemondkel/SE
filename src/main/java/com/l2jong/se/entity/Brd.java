package com.l2jong.se.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "SE_TB_BRD")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Brd {

	@Id
	@GeneratedValue
	private Long no;
	private char viewType;
	private String title;
	private String content;
	private char delYn;

	@ManyToOne
	@JoinColumn(name = "USR_NO")
	private Long usrNo;
	private Date createdAt;
	private Date updatedAt;
}
