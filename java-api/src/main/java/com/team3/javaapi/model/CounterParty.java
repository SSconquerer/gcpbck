package com.team3.javaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "counter_party")
@Data
public class CounterParty {
	 	
		@Id
		@Column(name = "counter_party_id")
	    private long id;
		
		@Column(name = "countter_party_name")
	    private String name;

	    
}