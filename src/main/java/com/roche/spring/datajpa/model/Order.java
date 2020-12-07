package com.roche.spring.datajpa.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

	@Id
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "creationDateTime")
	private LocalDateTime creationDateTime;

	@ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinTable(name="Order_Products",
			joinColumns= {@JoinColumn(name="ORDER_ID")},
			inverseJoinColumns= {@JoinColumn(name="PRODUCT_ID")})
    private List<Product> products;

		   	
	public double getTotalAmount() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }	
	
	public Order() 
	{
		products = new ArrayList<>();
        creationDateTime = LocalDateTime.now();
	}
	
	public Order(Long id, String email, List<Product> products) 
	{
		this.id = id;
		this.email = email;
		this.products = products;
        this.creationDateTime = LocalDateTime.now();
	}

	public Long getId() 	
	{
		return id;
	}

	public void setId(Long id) 	
	{
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public LocalDateTime getCreationDateTime() 
	{
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) 
	{
		this.creationDateTime = creationDateTime;
	}
	
	public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
         
        if (!(o instanceof Order)) return false;
         
        return id != null && id.equals(((Order) o).getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    
}
