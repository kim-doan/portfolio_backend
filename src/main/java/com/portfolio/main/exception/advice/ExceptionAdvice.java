package com.portfolio.main.exception.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import com.portfolio.main.exception.CAboutNotFoundException;
import com.portfolio.main.exception.CAboutSaveException;
import com.portfolio.main.exception.CAuthenticationEntryPointException;
import com.portfolio.main.exception.CBoardDetailNotFoundException;
import com.portfolio.main.exception.CBoardSaveException;
import com.portfolio.main.exception.CLoginFailureException;
import com.portfolio.main.exception.CPasswordIncorrectException;
import com.portfolio.main.exception.CSignUpFailException;
import com.portfolio.main.exception.CUserDuplicateException;
import com.portfolio.main.exception.CUserNotFoundException;
import com.portfolio.main.response.model.CommonResult;
import com.portfolio.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	private final ResponseService responseService;

	private final MessageSource messageSource;

	private String getMessage(String code) {
		return getMessage(code, null);
	}

	private String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

	// 알수없는 오류
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult defaultException(HttpServletRequest request, Exception e) {
		return responseService.getFailResult("E0001", getMessage("unKnown.msg"));
	}

	// 유저 정보 조회 실패
	@ExceptionHandler(CUserNotFoundException.class)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		return responseService.getFailResult("E0002", getMessage("userNotFoundException.msg"));
	}

	// 유저 정보 중복
	@ExceptionHandler(CUserDuplicateException.class)
	protected CommonResult userDuplicateException(HttpServletRequest request, CUserDuplicateException e) {
		return responseService.getFailResult("E0003", getMessage("userDuplicateException.msg"));
	}

	// 회원가입 실패
	@ExceptionHandler(CSignUpFailException.class)
	protected CommonResult signUpFailException(HttpServletRequest request, CSignUpFailException e) {
		return responseService.getFailResult("E0004", getMessage("signUpFailException.msg"));
	}

	// 비밀번호 틀림
	@ExceptionHandler(CPasswordIncorrectException.class)
	protected CommonResult passwordIncorrectException(HttpServletRequest request, CPasswordIncorrectException e) {
		return responseService.getFailResult("E0005", getMessage("passwordIncorrectException.msg"));
	}

	// 로그인 실패
	@ExceptionHandler(CLoginFailureException.class)
	protected CommonResult loginFailureException(HttpServletRequest request, CLoginFailureException e) {
		return responseService.getFailResult("E0006", getMessage("loginFailureException.msg"));
	}

	// 토큰 만료 및 없을 경우 오류 처리
	@ExceptionHandler(CAuthenticationEntryPointException.class)
	public CommonResult authenticationEntryPointException(HttpServletRequest request,
			CAuthenticationEntryPointException e) {
		return responseService.getFailResult("E0007", getMessage("authenticationEntryPointException.msg"));
	}

	// 토큰 권한 오류
	@ExceptionHandler(AccessDeniedException.class)
	public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
		return responseService.getFailResult("E0008", getMessage("accessDeniedException.msg"));
	}

	// 소개 정보 조회 실패
	@ExceptionHandler(CAboutNotFoundException.class)
	public CommonResult aboutNotFoundException(HttpServletRequest request, CAboutNotFoundException e) {
		return responseService.getFailResult("E0009", getMessage("aboutNotFoundException.msg"));
	}

	// 소개 정보 저장 실패
	@ExceptionHandler(CAboutSaveException.class)
	public CommonResult aboutSaveException(HttpServletRequest request, CAboutSaveException e) {
		return responseService.getFailResult("E0010", getMessage("aboutSaveException.msg"));
	}
	
	// 게시판 저장 실패
	@ExceptionHandler(CBoardSaveException.class)
	public CommonResult boardSaveException(HttpServletRequest request, CBoardSaveException e) {
		return responseService.getFailResult("E0011", getMessage("boardSaveException.msg"));
	}
	
	// 게시판 상세정보 조회 실패
	@ExceptionHandler(CBoardDetailNotFoundException.class)
	public CommonResult boardDetailNotFoundException(HttpServletRequest request, CBoardDetailNotFoundException e) {
		return responseService.getFailResult("E0012", getMessage("boardDetailNotFoundException.msg"));
	}
	
}
