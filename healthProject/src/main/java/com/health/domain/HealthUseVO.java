package com.health.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthUseVO {

	private int healthUseNo;	// 회원권 key
	private String name;		// 회원권명
	private String startDate;	// 시작일
	private String endDate;		// 종료일
	private int usingHealth;	// 회차
	private String memid;		// 사용자 ID
	
}
