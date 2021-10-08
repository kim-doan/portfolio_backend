package com.portfolio.main.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.main.response.model.SingleResult;
import com.portfolio.main.response.service.ResponseService;
import com.portfolio.main.system.dto.SystemOptionDTO;
import com.portfolio.main.system.model.SystemOption;
import com.portfolio.main.system.service.SystemOptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SystemOptionController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private SystemOptionService systemOprtionService;
	
	@CrossOrigin
	@GetMapping("/systemOption")
	private SingleResult<SystemOptionDTO> getSystemOption() {
		return responseService.getSingleResult(systemOprtionService.getSystemOption());
	}
	
	@CrossOrigin
	@PostMapping("/systemOption/save")
	private SingleResult<SystemOptionDTO> setSystemOption(@RequestBody(required = true) SystemOption systemOption) {
		SystemOptionDTO result = systemOprtionService.setSystemOption(systemOption);
		
		return responseService.getSingleResult(result);
	}
}
