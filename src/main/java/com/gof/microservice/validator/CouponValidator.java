package com.gof.microservice.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ctc.wstx.util.StringUtil;
import com.gof.microservice.exception.ApiError;
import com.gof.microservice.models.Coupon;
@Component
public class CouponValidator implements Validator {
	
	@Autowired
	ApiError apiError=new ApiError();

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(Coupon.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		Coupon c =(Coupon)target;
		
		if(StringUtils.isEmpty(c.getCouponCode().toString())) {
			errors.reject("couponCode");
			apiError.getValidationError().add("Please Provide Coupon Code");
			
		}
		if(StringUtils.isEmpty(c.getCouponId().toString())) {
			errors.reject("couponId");
			apiError.getValidationError().add("Please Provide couponId ");
			
		}
		if(StringUtils.isEmpty(c.getCouponDisc().toString())) {
			errors.reject("couponDisc");
			apiError.getValidationError().add("Please Provide coupon Discount");
			
		}
		if(c.getCouponDisc()<0) {
			errors.reject("couponDisc");
			apiError.getValidationError().add("Please Provide Valid Discount");
			
		}	
		if(StringUtils.isEmpty(c.getExpDate())) {
			errors.reject("extDate");
			apiError.getValidationError().add("Please Provide coupon Expiry Date");
			
		}
//		if(c.getExpDate().before(new Date())) {
//			errors.reject("extDate");
//			apiError.getValidationError().add("Please Provide Valid Expiry Date");
//			
//		}
	}

}
