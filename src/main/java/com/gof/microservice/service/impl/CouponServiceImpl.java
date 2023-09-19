package com.gof.microservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gof.microservice.models.Coupon;
import com.gof.microservice.repositories.CouponRepository;
import com.gof.microservice.service.CouponService;


@Service
public class CouponServiceImpl implements CouponService{
	
	private CouponRepository repository;
	

	@Override
	@Transactional
	public Coupon saveCoupon(Coupon coupon) {
	
		return repository.save(coupon);
	}

	@Override
	@Transactional
	@CachePut(value="coupon-cache",key="#couponId")
	public Coupon updateCoupon(Coupon coupon) {
		
		return repository.save(coupon);
	}

	@Override
	@Transactional(readOnly=true)
	@Cacheable(value="coupon-cache",key="#couponId")
	public Coupon getCouponById(Integer couponId) {
		Optional<Coupon> c=repository.findById(couponId);
		return c.isPresent()?c.get():null;
	}

	@Override
	@Transactional(readOnly=true)
	public Coupon getCouponByCode(String Code) {

		return repository.findByCouponCode(Code);
	}

	@Override
	@Transactional
	@CacheEvict(value="coupon-cache",key="#couponId")
	public void deleteCouponById(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Coupon> getAllCoupon() {
		
		return repository.findAll();
	}

	@Override
	
	public boolean isExist(Integer id) {
		return repository.existsById(id);
		
	}

	@Override
	public boolean isExpired(String code) {
		Coupon findByCouponCodeAndExtDate = repository.findByCouponCodeAndExtDate(code, new Date());
		
		return (findByCouponCodeAndExtDate!=null)?true:false;
	}

}
