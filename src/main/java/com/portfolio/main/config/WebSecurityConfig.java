package com.portfolio.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.portfolio.main.jwt.JwtAuthenticationFilter;
import com.portfolio.main.jwt.JwtTokenProvider;
import com.portfolio.main.security.handler.CustomAccessDeniedHandler;
import com.portfolio.main.security.handler.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable() // 기본설정 사용안함
			.csrf().disable() // CSRF 보안 필요없음
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT TOKEN으로 인증하므로 세션이필요없음
			.and()
				.authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(HttpMethod.GET, "/", "/exception/**", "/actuator/health").permitAll()
				.antMatchers("/api/userInfo").hasRole("ADMIN")
				.anyRequest().permitAll()
			.and()
				.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // 토큰 권한이 낮을경우 핸들링
			.and()
				.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 토큰이 없거나 토큰이 만료되었을 경우 핸들링
			.and()
				//jwt token 필터를 id/password 인증 필터 전에 넣는다
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/resources/static/**", "/resources/static/static/**", "/static/js/**", "/static/css/**", "/static/img/**"
				, "**/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
