package com.portfolio.main.about.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class About {
	@Id
	private String name;
	
	private String email;
	
	private String phoneNo;
	
	private int career;
	
	private List<AboutDetail> careerList = new ArrayList<AboutDetail>();
	
	private List<AboutDetail> projectList = new ArrayList<AboutDetail>();
	
	private List<TechStack> stackList = new ArrayList<TechStack>();
}
