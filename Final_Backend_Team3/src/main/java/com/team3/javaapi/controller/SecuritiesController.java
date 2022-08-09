package com.team3.javaapi.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.team3.javaapi.model.Securities;
import com.team3.javaapi.model.Trades;
import com.team3.javaapi.repository.SecuritiesRepository;
import com.team3.javaapi.repository.TradeRepository;

@RestController
@RequestMapping("/api/v1/securities")
public class SecuritiesController {
	@Autowired
	private SecuritiesRepository securitiesRepository;
	@Autowired
	private TradeRepository tradeRepository;
	
	@GetMapping("/all")
	public List<Securities> getAllSecurties() {
		return securitiesRepository.findAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Securities> getSecuritiesById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Securities securities = securitiesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Security not found for this id :: " + id));
		return ResponseEntity.ok().body(securities);
	}

	@GetMapping("/getByDate/{startDate}/to/{endDate}")
	public List<Securities> getSecuritiesById(@PathVariable(value = "startDate") Date startDate,
			@PathVariable(value = "endDate") Date endDate) {
		List<Securities> securities = securitiesRepository.findAll();
		return securities.stream()
				.filter(e -> !e.getMaturityDate().before(startDate) && !e.getMaturityDate().after(endDate)).sorted()
				.collect(Collectors.toList());
	}
	
	@GetMapping("/getAllTradesForSecurity/{id}")
	public List<Trades> getTradesForSecurity(@PathVariable(value = "id") Long id){
		List<Trades> trades = tradeRepository.findAll();
		List<Trades> filteredTrades = trades.stream()
				.filter(e -> e.getSid().equals(id))
				.collect(Collectors.toList());
		return filteredTrades;
	}

	@PostMapping("/createSecurity")
	public Securities createSecurities(@Valid @RequestBody Securities securities) {
		return securitiesRepository.saveAndFlush(securities);
	}

	@PutMapping("/updateSecurity/{id}")
	public ResponseEntity<Securities> updateSecurities(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Securities securityDetails) throws ResourceNotFoundException {
		Securities getSecurities = securitiesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Securities not found for this id :: " + id));

		getSecurities.setIsin(securityDetails.getIsin());
		getSecurities.setCusip(securityDetails.getCusip());
		getSecurities.setIssuer(securityDetails.getIssuer());
		getSecurities.setCoupon(securityDetails.getCoupon());
		getSecurities.setType(securityDetails.getType());
		getSecurities.setFaceValue(securityDetails.getFaceValue());
		getSecurities.setStatus(securityDetails.getStatus());
		getSecurities.setMaturityDate(securityDetails.getMaturityDate());

		final Securities updatedSecurities = securitiesRepository.save(getSecurities);
		return ResponseEntity.ok(updatedSecurities);
	}

	@DeleteMapping("/deleteSecurity/{id}")
	public Map<String, Boolean> deleteDog(@PathVariable(value = "id") Long id) throws Exception {
		Securities securities = securitiesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Securities not found for this id :: " + id));

		securitiesRepository.delete(securities);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
