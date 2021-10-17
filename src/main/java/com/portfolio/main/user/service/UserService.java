package com.portfolio.main.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.portfolio.main.exception.CAuthenticationEntryPointException;
import com.portfolio.main.exception.CLoginFailureException;
import com.portfolio.main.exception.CPasswordIncorrectException;
import com.portfolio.main.exception.CUserDuplicateException;
import com.portfolio.main.exception.CUserNotFoundException;
import com.portfolio.main.jwt.JwtTokenProvider;
import com.portfolio.main.repository.UserRepository;
import com.portfolio.main.user.model.User;
import com.portfolio.main.user.param.AuthenticationRequest;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/**
	 * 유저정보 전체 조회 (페이지네이션)
	 * @param pageable
	 * @return
	 */
	public Page<User> getUserAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	/**
	 * 유저아이디로 유저정보 단건 조회
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserInfo(String userId) {
		return userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
	}

	/**
	 * 회원가입
	 * 
	 * @param param
	 * @return
	 */
	public boolean signUp(AuthenticationRequest param) {
		boolean success = true;

		try {
			Optional<User> idCheck = userRepository.findByUserId(param.getUserId());

			if (idCheck.isPresent()) {
				throw new CUserDuplicateException();
			} else {
				List<String> roles = new ArrayList<String>();
				roles.add("ROLE_USER");

				User user = User.builder().userNo(ObjectId.get()).userId(param.getUserId())
						.userName(param.getUserName()).userPassword(passwordEncoder.encode(param.getUserPassword()))
						.createTime(new Date()).enabled(true).roles(roles).build();

				User newUser = userRepository.save(user);

				success = newUser.getUserNo() != null ? true : false;
			}
		} catch (CUserDuplicateException e) {
			throw new CUserDuplicateException();
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}

	/**
	 * 로그인
	 * 
	 * @param param
	 * @return
	 */
	public String signIn(AuthenticationRequest param) {
		String token = "";

		try {
			User loginUser = userRepository.findByUserId(param.getUserId()).orElseThrow(CUserNotFoundException::new);

			if (passwordEncoder.matches(param.getUserPassword(), loginUser.getUserPassword())) {
				token = jwtTokenProvider.createToken(loginUser.getUserId(), loginUser.getRoles());
			} else {
				throw new CPasswordIncorrectException();
			}

		} catch (CPasswordIncorrectException e) {
			throw new CPasswordIncorrectException();
		} catch (CUserNotFoundException e) {
			throw new CUserNotFoundException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CLoginFailureException();
		}

		return token;
	}

	/**
	 * 유저 프로필
	 * 
	 * @param token
	 * @return
	 */
	public User getProfile(String token) {
			if (jwtTokenProvider.validateToken(token)) {
				return getUserInfo(jwtTokenProvider.getUserPK(token));
			} else {
				throw new CAuthenticationEntryPointException();
			}
	}
}
