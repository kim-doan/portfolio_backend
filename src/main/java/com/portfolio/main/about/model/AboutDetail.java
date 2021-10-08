package com.portfolio.main.about.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import lombok.Setter;

import lombok.Getter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AboutDetail {
	private Date startDate;
	
	private Date endDate;
	
	private String contents;
}
