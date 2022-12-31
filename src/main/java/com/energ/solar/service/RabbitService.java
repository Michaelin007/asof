package com.energ.solar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.energ.solar.model.Rabbit;
import com.energ.solar.repository.RabbitRepository;



public class RabbitService {
	@Autowired
	RabbitRepository rabbitRepository;
	
	
	public Page <Rabbit> findAllProduct(int pagenumber,Pageable pageable, String sortField, String sortDir){
		
		Sort sort= Sort.by("name");
		sort=sortDir.equals("asc")? sort.ascending():sort.descending();
		 pageable= PageRequest.of(pagenumber-1, 10,sort);
		return (Page<Rabbit>) rabbitRepository.findAll(pageable);
		
	}
	
	public void saveProduct(Rabbit product) {
		this.rabbitRepository.save(product);
	}
	

	public Rabbit findById(long id) {
		Optional<Rabbit> optional = rabbitRepository.findById(id);
		Rabbit product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return product;
	}
	
	public void deleteProduct(long id) {
		rabbitRepository.deleteById(id);

	}
	


}
