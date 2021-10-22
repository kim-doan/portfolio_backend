package com.portfolio.main.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.main.file.model.FsFilesetModel;
import com.portfolio.main.repository.FsFilesetRepository;

@Service
public class FileService {
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	@Autowired
	private GridFsOperations operations;
	
	@Autowired
	private FsFilesetRepository fsFileSetRepository;
	
	public String addFile(MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		String filename = file.getOriginalFilename();
		String contentType = file.getContentType();		
		ObjectId id = gridFsTemplate.store(inputStream, filename, contentType);
		return id.toString();
	}

	public String addFiles(String[] fileCodes, MultipartFile[] files) throws IOException {	
		List<String> fileIds = new ArrayList<String>();
		int multipartFileIndex = 0;
		
		for(String fileCode : fileCodes) {
			if(fileCode.equals("NEW")) {
				fileIds.add(addFile(files[multipartFileIndex]));
				multipartFileIndex++;
			} else {
				fileIds.add(fileCode);
			}
		}
		
		FsFilesetModel fsFileset = MakeFileset(fileIds);
		return fsFileset.getObjectId();
	}
	
	public FsFilesetModel MakeFileset(List<String> fileIds) {
		
		FsFilesetModel entity = FsFilesetModel.builder()
											  .objectId("FS" + new ObjectId())
                							  .fileIds(fileIds)
                							  .build();
	
		return fsFileSetRepository.save(entity);
	}
}
