package com.roche.spring.datajpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.roche.spring.datajpa.model.Order;
import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Long>
{
	@Query("Select o From Order o where o.creationDateTime BETWEEN :startDate AND :endDate")
	List<Order> findByDates(@Param(value="startDate") LocalDateTime startDate, @Param(value="endDate") LocalDateTime endDate);
}
