package com.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.health.entity.HealthProVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "program")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthProVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String date;
	
	@Column(nullable = false)
	private int times;
	
	@Column(nullable = false)
	private int totalPerson;
}