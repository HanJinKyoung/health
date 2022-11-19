package com.health.domain;

import lombok.Data;

@Data
public class HealthProVO {

	private int pid;			//프로그램 id
	private String name;		//프로그램명
	private String date;		//프로그램 날짜
	private int times;			//날짜 별 회차
	private int totalPerson;	//최대인원
	
}
