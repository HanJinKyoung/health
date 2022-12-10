package com.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "healthUse")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthUseVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int healthUseNo;
	
	@Column(length = 200, nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String startDate;
	
	@Column(length = 20, nullable = false)
	private String endDate;
	
	@Column(nullable = false)
	private int usingHealth;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private MemberVO memId;

}