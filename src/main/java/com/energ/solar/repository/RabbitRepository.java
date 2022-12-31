package com.energ.solar.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.energ.solar.model.Rabbit;


public interface RabbitRepository extends PagingAndSortingRepository <Rabbit,Long> {
	

}
