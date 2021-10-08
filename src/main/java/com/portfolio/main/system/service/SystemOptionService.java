package com.portfolio.main.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.main.repository.SystemOptionRepository;
import com.portfolio.main.system.dto.SystemOptionDTO;
import com.portfolio.main.system.model.SystemOption;

@Service
@Transactional
public class SystemOptionService {
	@Autowired
	private SystemOptionRepository systemOptionRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * 프로그램 옵션정보 가져오기
	 * @return
	 */
	public SystemOptionDTO getSystemOption() {
		List<SystemOption> optionList = systemOptionRepository.findAll();
		Map<String, String> keyValueMap = listToKeyValueMap(optionList);
		SystemOptionDTO dto = objectMapper.convertValue(keyValueMap, SystemOptionDTO.class);
		
		return dto;
	}
	
	/**
	 * 프로그램 옵션 설정하기
	 * @param systemOption
	 * @return
	 */
	public SystemOptionDTO setSystemOption (SystemOption systemOption) {
		systemOptionRepository.save(systemOption);
		
		return getSystemOption();
	}
	
	private Map<String, String> listToKeyValueMap(List<SystemOption> optionList){	
		if(optionList.size() <= 0) {
			return new HashMap<String, String>();
		}
		Map<String, String> optionMap = new HashMap<>();
		for (SystemOption option : optionList) {
			optionMap.put(option.getOptionKey(), option.getOptionValue());
		}		
		return optionMap;
	}
}
