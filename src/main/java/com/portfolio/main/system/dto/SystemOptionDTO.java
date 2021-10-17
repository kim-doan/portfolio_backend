package com.portfolio.main.system.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SystemOptionDTO {
	/**
	 * 사이트 소유자 이름
	 */
	private String owner;
}
