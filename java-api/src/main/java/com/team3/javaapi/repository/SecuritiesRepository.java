package com.team3.javaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.javaapi.model.Securities;

@Repository
public interface SecuritiesRepository extends JpaRepository<Securities, Long>{

}