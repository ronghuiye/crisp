package com.ryan.crisp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessor {

	void setValidators(List<String> validators);
	void setColumnOrder(HashMap<String,Integer> columnOrder);
	void process(MultipartFile file) throws IOException;
}
