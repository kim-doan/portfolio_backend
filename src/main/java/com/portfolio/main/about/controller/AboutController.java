package com.portfolio.main.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.main.about.model.About;
import com.portfolio.main.about.service.AboutService;
import com.portfolio.main.exception.CAboutSaveException;
import com.portfolio.main.response.model.CommonResult;
import com.portfolio.main.response.model.SingleResult;
import com.portfolio.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AboutController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private AboutService aboutService;
	
	@CrossOrigin
	@GetMapping("/aboutInfo")
	private SingleResult<About> getAbout() {
		return responseService.getSingleResult(aboutService.getAbout());
	}
	
	@CrossOrigin
	@PostMapping("/aboutInfo/save")
	private CommonResult setAbout(@RequestBody(required = true) About about) {
		boolean result = aboutService.setAbout(about);
		
		if(result) {
			return responseService.getSuccessResult();
		} else {
			throw new CAboutSaveException();
		}
	}
}
