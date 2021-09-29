package com.portfolio.main.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.main.exception.CSignUpFailException;
import com.portfolio.main.response.model.CommonResult;
import com.portfolio.main.response.model.LoginResult;
import com.portfolio.main.response.model.SingleResult;
import com.portfolio.main.response.service.ResponseService;
import com.portfolio.main.user.model.User;
import com.portfolio.main.user.param.AuthenticationRequest;
import com.portfolio.main.user.service.UserService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@GetMapping("/userInfo")
	private SingleResult<User> getUserInfo(@RequestParam(name = "userId") String userId) {
		return responseService.getSingleResult(userService.getUserInfo(userId));
	}
	
	@CrossOrigin
	@PostMapping("/signUp")
	private CommonResult signUp(@RequestBody(required = true) AuthenticationRequest param) {
		boolean result = userService.signUp(param);
		
		if(result) {
			return responseService.getSuccessResult();
		} else {
			throw new CSignUpFailException();
		}
	}
	
	@CrossOrigin
	@PostMapping("/signIn")
	private LoginResult signIn(@RequestBody(required = true) AuthenticationRequest param) {
		String token = userService.signIn(param);
		
		return responseService.getLoginResult(token);
	}
	
	@CrossOrigin
	@GetMapping("/profile")
	private SingleResult<User> profile(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
		return responseService.getSingleResult(userService.getProfile(token));
	}
}
