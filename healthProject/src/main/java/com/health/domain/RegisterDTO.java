package com.health.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

	private int id;
	private int healthUseNo;	//이용권id
	private int pid;			//프로그램id
	
}
