package com.portfolio.main.user.param;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationRequest {
	private String userId;
	private String userPassword;
	private String userName;
}
