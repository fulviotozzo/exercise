package com.roche.spring.datajpa.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

	@Id
	private Long SKU;

	@Column(name = "productName", nullable = false)
	private String productName;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "creationDate")
	private LocalDate creationDate;
	
	public Product() 
	{
		
	}
	
	public Product(Long SKU, String productName, double price, LocalDate creationDate) 
	{
		this.SKU = SKU;
		this.productName = productName;
		this.price = price;
		this.creationDate = creationDate;
	}

	public Long getSku() {
		return SKU;
	}
	
	public void setSku(Long sku) {
		this.SKU = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "Product [SKU=" + SKU + ", productName=" + productName + ", price=" + price + ", creationDate=" + creationDate + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
             
        if (!(o instanceof Product))
            return false;
         
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(SKU, productName, price, creationDate);
    }
}
