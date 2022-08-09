package com.team3.javaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.javaapi.model.Trades;

@Repository
public interface TradeRepository extends JpaRepository<Trades, Long>{

}