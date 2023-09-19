package com.gof.microservice.service;

import java.util.List;

import com.gof.microservice.models.Coupon;

public interface CouponService {
	
	
	public Coupon saveCoupon(Coupon coupon );
	public Coupon updateCoupon(Coupon coupon );
	public Coupon getCouponById(Integer id );
	public Coupon getCouponByCode(String Code );
	public void deleteCouponById(Integer id);
	public List<Coupon> getAllCoupon();
	public boolean isExist(Integer id );
	public boolean isExpired(String code);
	
	
	

}
