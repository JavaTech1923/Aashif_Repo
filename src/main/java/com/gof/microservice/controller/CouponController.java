package com.gof.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.microservice.exception.CouponNotFoundException;
import com.gof.microservice.exception.ValidationError;
import com.gof.microservice.models.Coupon;
import com.gof.microservice.repositories.CouponRepository;
import com.gof.microservice.service.CouponService;
import com.gof.microservice.validator.CouponValidator;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CouponValidator couponValidator;

	@PostMapping("/save")
	public ResponseEntity<String> saveCoupon(@RequestBody Coupon coupon, Errors errors) {
		couponValidator.validate(coupon, errors);
		if (!errors.hasErrors()) {
			System.out.println("this is the save url");
			couponRepository.save(coupon);
			return new ResponseEntity<String>("Coupon has added successfully", HttpStatus.OK);
		} else {
			throw new ValidationError("Please provide Valid Details");
		}
	}

	@GetMapping("/check/{code}")
	public String checkExpiredOrNot(@PathVariable String id) {
		return couponRepository.existsById(null) ? "Not Expired" : "Expired";

	}

	@GetMapping("/getOne/{id}")
//	@HystrixCommand(fallbackMethod ="getCouponException")
	public Coupon getOneCoupon(@PathVariable Integer id) {
		Coupon c = couponService.getCouponById(id);
		if (c != null) {
			return c;

		} else {
			throw new CouponNotFoundException("No Coupon Found Exception");

		}
	}

	@GetMapping("/getByCode/{code}")
//	@HystrixCommand(fallbackMethod = "getCouponCodeException")
	public Coupon getCouponByCode(@PathVariable String code) {
		System.out.println(code);
		Coupon c = couponService.getCouponByCode(code);
		if (c != null) {
			return c;
		} else {
			throw new CouponNotFoundException("No Coupon found");

		}
	}
	// fallback method ( Method name must be same as fall back name )

	public Coupon getCouponCodeException(String id) {
		throw new CouponNotFoundException("No Coupon Found");

	}

	public Coupon getCouponException(Integer id) {
		throw new CouponNotFoundException("No Coupon Found");

	}

	@GetMapping("/all")
	public List<Coupon> getAllCoupon() {
		List<Coupon> list = couponService.getAllCoupon();
		System.out.println(list);
		return list;

	}

	@PostMapping("/update")
	public ResponseEntity<String> updateCoupon(@RequestBody Coupon coupon, Errors errors) {
		if (couponService.isExist(coupon.getCouponId())) {
			couponValidator.validate(coupon, errors);
			if (!errors.hasErrors()) {
				couponService.saveCoupon(coupon);
				return new ResponseEntity<String>("Coupon has been updated Successfully ", HttpStatus.OK);

			} else {
				throw new CouponNotFoundException("No Coupon Found");
			}

		} else {
			throw new CouponNotFoundException("No Coupon Found");
		}

	}
	
	public ResponseEntity<String> deleteCouponById(@PathVariable Integer Id){
		if(couponService.isExist(Id)) {
			couponService.deleteCouponById(Id);
			return new ResponseEntity<String>("Coupon Deleted Successfully",HttpStatus.OK);
			
		}else {
			throw new CouponNotFoundException("No Coupon Found");
			
		}
	}

}
