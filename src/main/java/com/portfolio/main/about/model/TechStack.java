package com.portfolio.main.about.model;

import java.util.Date;

import com.portfolio.main.about.model.AboutDetail.AboutDetailBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TechStack {
	private String stackCtg;
	
	private String stackName;
	
	private Integer stackGuage;
	
	private byte[] icon;
}
