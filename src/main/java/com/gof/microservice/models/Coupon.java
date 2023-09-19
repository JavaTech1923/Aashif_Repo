package com.gof.microservice.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="coupon_micro")
public class Coupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="coupon_Id")
	private Integer couponId;
	@Column(name="coupon_code", length=25)
	private String couponCode;
	@Column(name="coupon_discount")
	private Double couponDisc;

	@Column(name="coupan_expiry")
	@Temporal(TemporalType.DATE)
	private Date expDate;
	
	@JsonIgnore
	@Column(name="coupon_valid")
	private boolean isValid=true;
	
	

}
