package com.portfolio.main.board.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.portfolio.main.board.model.Board.BoardBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class BoardDetail {
	@Id
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId boardId;
	
	private String contents;
	
	private String[] fileCodes;
}
