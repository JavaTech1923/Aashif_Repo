package com.gof.microservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gof.microservice.exception.ApiError;
import com.gof.microservice.exception.CouponNotFoundException;
import com.gof.microservice.exception.ValidationError;

@RestControllerAdvice
public class ExceptionController {
	
	@Autowired
	private ApiError apiError;
	
	@ExceptionHandler(value=CouponNotFoundException.class)
	public ResponseEntity<ApiError> noCouponFound(){
		
//		ApiError error=new ApiError();
		apiError.setErrorCode("400");
		apiError.setErrorDesc("No Coupon Found");
		apiError.setErrorDate(new Date());
		return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=ValidationError.class)
	public ResponseEntity<ApiError> validationError(){
		
//		ApiError error=new ApiError();
		apiError.setErrorCode("400");
		apiError.setErrorDesc("Please provide Valid Details");
		apiError.setErrorDate(new Date());
		return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
	}
	

}
