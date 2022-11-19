package com.health.domain;

import lombok.Data;

@Data
public class RegisterDTO {

	private int id;
	private int healthUseNo;	//이용권id
	private int pid;			//프로그램id
	
}
