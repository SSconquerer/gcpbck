package com.team3.javaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Books {

	@Id
	@Column(name = "book_id")
	private long id;

	@Column(name = "book_name")
	private String bookName;

}
