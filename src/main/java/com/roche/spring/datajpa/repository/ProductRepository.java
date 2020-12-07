package com.roche.spring.datajpa.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.roche.spring.datajpa.model.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>
{
	List<Product> findByProductNameContaining(String productName);
	
	@Query("Select p from Product p where p.creationDate BETWEEN :startDate AND :endDate")
	List<Product> findByDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
