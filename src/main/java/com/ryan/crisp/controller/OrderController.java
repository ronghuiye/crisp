package com.ryan.crisp.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryan.crisp.controller.dto.BaseResponse;
import com.ryan.crisp.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger = LogManager.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/transform")
    public ResponseEntity<BaseResponse> transform(@RequestParam("file") MultipartFile file){
				
		BaseResponse response = new BaseResponse();
		try {
			orderService.transform(file);
			response.setSuccess(true);
			response.setMessage("transform success");
		} catch (IOException e) {
			logger.error("Transform failed: {}",e.getMessage());
			response.setSuccess(false);
			response.setMessage("transform failed");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
