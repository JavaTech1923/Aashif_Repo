package com.gof.microservice.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gof.microservice.models.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	
	public Coupon findByCouponCode(String code);
	
	@Query("from com.gof.microservice.models.Coupon as c where c.couponCode=:couponCode and c.expDate>=:expDate")
	public Coupon findByCouponCodeAndExtDate(String couponCode, Date expDate);

}
