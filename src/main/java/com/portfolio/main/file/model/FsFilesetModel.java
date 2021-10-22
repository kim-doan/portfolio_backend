package com.portfolio.main.file.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@Document(collection = "fs.fileset")
@RequiredArgsConstructor
@SuperBuilder
public class FsFilesetModel{
	@Id
	private String objectId;
	private List<String> fileIds;
	
}
