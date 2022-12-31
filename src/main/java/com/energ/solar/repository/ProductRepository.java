package com.energ.solar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.energ.solar.model.Product;




public interface ProductRepository extends PagingAndSortingRepository<Product,Long>{
	
	@Query("SELECT p FROM Product p WHERE "
			+"CONCAT(p.id,p.pname,p.price,p.pdescrip)"
			+"LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);
	
}
