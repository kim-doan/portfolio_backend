package com.portfolio.main.board.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.main.board.dto.BoardDTO;
import com.portfolio.main.board.model.Board;
import com.portfolio.main.board.model.BoardDetail;
import com.portfolio.main.exception.CBoardDetailNotFoundException;
import com.portfolio.main.repository.BoardDetailRepository;
import com.portfolio.main.repository.BoardRepository;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardDetailRepository boardDetailRepository;
	
	public Page<BoardDTO> getBaordPage(Pageable pageable) {
		Page<Board> output = boardRepository.findAll(pageable);
		
		List<BoardDTO> dtoList = new ArrayList<BoardDTO>();
		
		for (Board model : output) {
			BoardDTO dto = BoardDTO.builder()
					.boardId(model.getBoardId())
					.title(model.getTitle())
					.thumbnail(model.getThumbnail())
					.createUser(model.getCreateUser())
					.createTime(model.getCreateTime())
					.used(model.getUsed())
					.build();
			
			dtoList.add(dto);
		}
		
		return new PageImpl<BoardDTO>(dtoList, pageable, output.getTotalElements());
	}
	
	public Board getBoardDetail(ObjectId boardId) {
		return boardRepository.findById(boardId).orElseThrow(CBoardDetailNotFoundException::new);
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
