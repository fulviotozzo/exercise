package com.roche.spring.datajpa.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roche.spring.datajpa.model.Order;
import com.roche.spring.datajpa.model.Product;
import com.roche.spring.datajpa.repository.OrderRepository;
import com.roche.spring.datajpa.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrderController 
{

  @Autowired
  OrderRepository orderRepository;
  
  @Autowired
  ProductRepository productRepository;

  @GetMapping("/orders")
  public ResponseEntity<List<Order>> getAllOrders() 
  {
	  try 
	  {
		  List<Order> orders = new ArrayList<Order>();

		  if (orders.isEmpty()) 
		  {
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		  return new ResponseEntity<>(orders, HttpStatus.OK);
	  } 
	  catch (Exception e) 
	  {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) 
  {
	  Optional<Order> orderData = orderRepository.findById(id);
	  
	  if (orderData.isPresent()) 
	  {
		  return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
	  } 
	  else 
	  {
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
  }

  @PostMapping("/orders")
  public ResponseEntity<Order> createOrder(@RequestBody Order order) 
  {
	  
	  if (orderRepository.existsById(order.getId())) 
	  {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      List<Long> skuList = order.getProducts().stream().map(x -> x.getSku()).collect(Collectors.toList());
      List<Product> existingProducts = new ArrayList<>();
      productRepository.findAllById(skuList).forEach(x ->existingProducts.add(x));
             
      if(skuList.size() != existingProducts.size())
      {
    	  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
	  
	  try 
	  {	  
		  Order _order = orderRepository.save(new Order(order.getId(), order.getEmail(), order.getProducts()));
		  return new ResponseEntity<>(_order, HttpStatus.CREATED);
	  } 
	  catch (Exception e) 
	  {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }


  @GetMapping("/orders/date")
  public ResponseEntity<List<Order>> findByDates(@RequestParam("from") String from, @RequestParam("to") String to) 
  {
	  try 
	  {
		  LocalDateTime startDate = LocalDateTime.parse(from);
		  LocalDateTime endDate = LocalDateTime.parse(to);
		  
		  List<Order> orders = orderRepository.findByDates(startDate, endDate);
		  if (orders.isEmpty()) 
		  {
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		  return new ResponseEntity<>(orders, HttpStatus.OK);
	  } 
	  catch (Exception e) 
	  {
		  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }
}
