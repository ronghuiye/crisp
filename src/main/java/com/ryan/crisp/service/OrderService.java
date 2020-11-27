package com.ryan.crisp.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryan.crisp.config.OrderConfig;
import com.ryan.crisp.config.ServiceFactory;


@Service
public class OrderService {
	
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	
	@Autowired
	private OrderConfig config;
	
	@Autowired
	private ServiceFactory serviceFactory;
	
	public void transform(MultipartFile file) throws IOException {
		
		String extension = file.getOriginalFilename().split("\\.")[1].toUpperCase();
		
		logger.debug("Transforming with {} file processor.", extension);
		FileProcessor processor = serviceFactory.getFileProcessor(extension);
		
		processor.setValidators(config.getValidations());
		processor.setColumnOrder(config.getColumns());
		processor.process(file);
		
		logger.debug("Finished Transforming");
	}
	
}
