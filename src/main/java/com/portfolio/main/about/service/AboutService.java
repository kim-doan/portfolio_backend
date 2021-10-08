package com.portfolio.main.about.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.main.about.model.About;
import com.portfolio.main.exception.CAboutNotFoundException;
import com.portfolio.main.repository.AboutRepository;
import com.portfolio.main.system.service.SystemOptionService;

@Service
@Transactional
public class AboutService {
	@Autowired
	private AboutRepository aboutRepository;
	
	@Autowired
	private SystemOptionService systemOptionService;
	
	public About getAbout() {
		String owner = systemOptionService.getSystemOption().getOwner();
		
		return aboutRepository.findById(owner).orElseThrow(CAboutNotFoundException::new);
	}
	
	public boolean setAbout(About about) {
		About model = aboutRepository.save(about);
		
		return model != null ? true : false;
	}
}
