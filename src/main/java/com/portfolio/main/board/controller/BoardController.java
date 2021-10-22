package com.portfolio.main.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.main.board.model.Board;
import com.portfolio.main.board.service.BoardService;
import com.portfolio.main.exception.CBoardSaveException;
import com.portfolio.main.response.model.CommonResult;
import com.portfolio.main.response.model.PageListResult;
import com.portfolio.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private BoardService boardService;
	
	@CrossOrigin
	@GetMapping("/board/page")
	public PageListResult<Board> getBoardPage(final Pageable pageable) {
		return responseService.getPageListResult(Board.class, boardService.getBaordPage(pageable));
	}
	
	@CrossOrigin
	@PostMapping("/board/save")
	public CommonResult setBoard(@RequestBody(required = true) Board board) {
		boolean result = boardService.setBoard(board);
		
		if(result) {
			return responseService.getSuccessResult();
		} else {
			throw new CBoardSaveException();
		}
	}
}
