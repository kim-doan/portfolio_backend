package com.portfolio.main.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.main.board.model.Board;
import com.portfolio.main.board.model.BoardDetail;
import com.portfolio.main.repository.BoardDetailRepository;
import com.portfolio.main.repository.BoardRepository;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardDetailRepository boardDetailRepository;
	
	public Page<Board> getBaordPage(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	public boolean setBoard(Board board) {
		boolean success = true;
		
		try {
			BoardDetail boardDetail = boardDetailRepository.save(board.getBoardDetail());
			board.setBoardDetail(boardDetail);
		
			Board model = boardRepository.save(board);
			
			success = model != null ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
}
