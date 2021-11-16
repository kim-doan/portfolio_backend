package com.portfolio.main.board.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.portfolio.main.board.model.BoardDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId boardId;
	
	private String title;
	
	private byte[] thumbnail;
	
	private String createUser;
	
	private Date createTime;
	
	private int used;
}
