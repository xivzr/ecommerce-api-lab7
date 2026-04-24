Ecommerce API

## Project Overview

This project is a Spring Boot REST API for managing products in an e-commerce system.
It supports basic CRUD operations such as creating, retrieving, updating, and deleting products.

The system also includes:

* Request validation (e.g., required fields, positive price)
* Global exception handling for clean error responses
* In-memory data storage (no database)

---

##  Setup Instructions

### Requirements

* Java 17 
* IntelliJ IDEA
* Postman (for testing API)

### Steps to Run

1. Open the project in IntelliJ
2. Locate EcommerceApiApplication.java
3. Click *Run*
4. Wait until you see:


     Tomcat started on port 8080 (http) with context path '/'


### Base URL *(For testing)*

http://localhost:8080/api/v1/products

---

##  API Endpoint Reference

###  GET All Products

* *Method:* GET
* *Path:* http://localhost:8080/api/v1/products
* *Description:* Retrieves all products
* *Response:*
     * 200 OK (valid/working)

###  GET Product by ID

* *Method:* GET
* *Path:* http://localhost:8080/api/v1/products/{id}
* *Description:* Retrieves a specific product by ID
* *Response:*

  * 200 OK (valid/working)
  * 404 Not Found (product does not exist)

###  CREATE Product

* *Method:* POST
* *Path:* /api/v1/products
* *Description:* Creates a new product
* *Response:*

  * 201 0K (valid/working)

###  UPDATE Product 

* *Method:* PUT
* *Path:* /api/v1/products/{id}
* *Description:* Updates all fields of a product
* *Response:*

  * 200k (valid/working)
  * 404 Not Found (product does not exist)
  * 400 Bad Request


###  PATCH Product (Partial Update)

* *Method:* PATCH
* *Path:* /api/v1/products/{id}
* *Description:* Updates selected fields of a product
* *Response:*

  * 200 OK (valid/working)
  * 404 Not Found (product does not exist)

###  DELETE Product

* *Method:* DELETE
* *Path:* /api/v1/products/{id}
* *Description:* Deletes a product
* *Response:*

  * 200 OK (valid/working)
  * 204 NO CONTENT
  * 404 Not Found (product does not exist)

## Sample Request & Response

### GET ALL Product (Valid)

* *Method:* GET
* *Request and Response:*


* 200K - **All list of products are displayed**
  ![Product_list1-200k.png](Lab7%20screenshot/Product_list1-200k.png)
  ![Product_list2-200k.png](Lab7%20screenshot/Product_list2-200k.png)
  ![Product_list3-200k.png](Lab7%20screenshot/Product_list3-200k.png)
  ![Product_list4-200k.png](Lab7%20screenshot/Product_list4-200k.png)

---

###  GET Product by ID
g
* *Method:* GET
* *Request and Response:*


* 200 OK
  ![TASK6.2-GET BY ID.png](Lab7%20screenshot/TASK6.2-GET%20BY%20ID.png)


* 404 Not Found - This is for product that does not exist
  ![task6-testInvalid-ID.png](Lab7%20screenshot/task6-testInvalid-ID.png)
---

###  CREATE Product

* *Method:* POST
* *Request and Response*


* 201K - This shows the created product. We added 3 products and automatically got ID number 11, 12, and 13.

![task6-post1-201.png](Lab7%20screenshot/task6-post1-201.png)
![task6-post2-201.png](Lab7%20screenshot/task6-post2-201.png)
![task6-post3-201.png](Lab7%20screenshot/task6-post3-201.png)

---

###  UPDATE Product

* *Method:* PUT
* *Request and Response*


* 200k
  ![PUT 200K.png](Lab7%20screenshot/PUT%20200K.png)


* 404 Not Found - Product not found using put method.
  ![404 not found -Put.png](Lab7%20screenshot/404%20not%20found%20-Put.png)


* 400 Bad Request
  ![put bad request.png](Lab7%20screenshot/put%20bad%20request.png)

###  PATCH Product (Partial Update)

* *Method:* PATCH
* *Request and Response*


* 200 OK - The product number with an ID number 11 is partially updated the price and stocks.
  ![Task6-partialupdate.png](Lab7%20screenshot/Task6-partialupdate.png)


* 404 Not Found - Product not found using patch method.
  ![404 not found -PATCH.png](Lab7%20screenshot/404%20not%20found%20-PATCH.png)

---

###  DELETE Product

* *Method:* DELETE
* *Request and Response*

* 200 OK - The product with ID number 2 is successfully deleted.
  ![DELETE NO CONTENT.png](Lab7%20screenshot/DELETE%20NO%20CONTENT.png)


* 204 NO CONTENT – This response appears because the delete mapping uses ResponseEntity.noContent().
  ![DELETE NO CONTENT.png](Lab7%20screenshot/DELETE%20NO%20CONTENT.png)


* 404 Not Found → If product not found
  ![404 not found -DELETE.png](Lab7%20screenshot/404%20not%20found%20-DELETE.png)
---



## Known Limitations

* The system uses *in-memory storage*
* Data is *not saved permanently*
* All products will be *deleted when the server restarts*
* No database integration (e.g., MySQL)
