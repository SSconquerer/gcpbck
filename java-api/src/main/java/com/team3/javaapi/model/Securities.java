package com.team3.javaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "securities")
@Data
public class Securities {

	@Id
	@Column(name = "security_id")
	private long id;

	@Column(name = "isin")
	private String isin;

	@Column(name = "cusip")
	private String cusip;

	@Column(name = "issuer")
	private String issuer;

	@Column(name = "coupon")
	private String coupon;

	@Column(name = "type")
	private String type;

	@Column(name = "face_value")
	private long faceValue;

	@Column(name = "status")
	private String status;

	@Column(name = "maturity_date")
	private Date maturityDate;

}
