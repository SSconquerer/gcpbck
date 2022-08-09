package com.team3.javaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "trades")
@Data
public class Trades {

	@Id
	@Column(name = "trade_id")
	private long id;

	@Column(name = "book_id")
	private long bookId;

	@Column(name = "cpid")
	private long cpid;

	@Column(name = "sid")
	private Long sid;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "status")
	private String status;

	@Column(name = "price")
	private double price;

	@Column(name = "buy_sell")
	private long buySell;

	@Column(name = "trade_date")
	private Date tradeDate;

	@Column(name = "settlement_date")
	private Date settlementDate;

}
