package com.l2jong.se.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "SE_TB_USR")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Usr {

	@Id
	@GeneratedValue
	private Long no;

	private String userId;
	private String nickname;
	private Date createdAt;
	private Date updatedAt;
}
