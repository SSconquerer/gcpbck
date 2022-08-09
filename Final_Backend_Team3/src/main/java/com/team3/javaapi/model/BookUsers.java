package com.team3.javaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "book_users")
@Data
public class BookUsers {

	@Id
	@Column(name = "book_id")
	private long bookId;

	@Column(name = "user_id")
	private long userId;

}