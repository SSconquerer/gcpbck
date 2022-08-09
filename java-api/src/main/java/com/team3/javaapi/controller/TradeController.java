package com.team3.javaapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.javaapi.exception.ResourceNotFoundException;
import com.team3.javaapi.model.Trades;
import com.team3.javaapi.repository.TradeRepository;

@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {
	
	@Autowired
	private TradeRepository tradeRepository;

	@GetMapping("/all")
	public List<Trades> getAllTrades() {
		return tradeRepository.findAll();
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Trades> getTradeById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Trades trade = tradeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trade not found for this id :: " + id));
		return ResponseEntity.ok().body(trade);
	}
	
	@PostMapping("/createTrade")
	public Trades createTrades(@Valid @RequestBody Trades trades) {
		return tradeRepository.saveAndFlush(trades);
	}
	
	@PutMapping("/updateTrade/{id}")
	public ResponseEntity<Trades> updateTrades(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Trades tradeDetails) throws ResourceNotFoundException {
		Trades getTrades = tradeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trades not found for this id :: " + id));

		getTrades.setBookId(tradeDetails.getBookId());
		getTrades.setCpid(tradeDetails.getCpid());
		getTrades.setSid(tradeDetails.getSid());
		getTrades.setQuantity(tradeDetails.getQuantity());
		getTrades.setStatus(tradeDetails.getStatus());
		getTrades.setBuySell(tradeDetails.getBuySell());
		getTrades.setPrice(tradeDetails.getPrice());
		getTrades.setTradeDate(tradeDetails.getTradeDate());
		getTrades.setSettlementDate(tradeDetails.getSettlementDate());

		final Trades updatedTrades = tradeRepository.save(getTrades);
		return ResponseEntity.ok(updatedTrades);
	}

	@DeleteMapping("/deleteTrade/{id}")
	public Map<String, Boolean> deleteTrade(@PathVariable(value = "id") Long id) throws Exception {
		Trades trades = tradeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trade not found for this id :: " + id));

		tradeRepository.delete(trades);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
