# exercise
 
 
This is the implementation of the product and create use cases with SpringBoot.
 
The data is stored in a MySQL database.
 
These methods have been implemented

POST
http://localhost:8080/api/products
Create a new product

GET
http://localhost:8080/api/products
Get all the products
http://localhost:8080/api/products/n
Get product(n)
http://localhost:8080/api/products?productName=n contains n
Get all the products that contain the string n in the name
http://localhost:8080/api/products/date?from=2014-01-01&to=2018-01-01
Get all the products created between from and to

PUT
http://localhost:8080/api/products?id=n
Update the product n

DELETE
http://localhost:8080/api/products
Delete all the products
http://localhost:8080/api/products/n
Delete product n

POST
http://localhost:8080/api/orders
Create an order
GET
http://localhost:8080/api/orders/date?from=2019-12-07T02:00:00&to=2019-12-07T04:00:00
Get all the orders between from and to

order json samples
{
     "id": "778",
	    "email": "ddd@aol.com",
     "products": 
	 [{
        "sku":"99",
		"productName": "product1",
		"price": "100",
		"creationDate": "2017-05-15"
     },
     {"sku":"88",
		"productName": "product2",
		"price": "200",
		"creationDate": "2018-05-15"}]    
}

BUILD

The Springboot project can be built using Maven while the dabase can be containerized using Docker. 
