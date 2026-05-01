Updated for Lab8

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
* *Path:* http://localhost:8080/api/v1/products
* *Description:* Creates a new product
* *Response:*

  * 201 0K (valid/working)

###  UPDATE Product 

* *Method:* PUT
* *Path:* http://localhost:8080/api/v1/products/{id}
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



-----




### Laboratory 8

## Overview
This project is a full-stack e-commerce application that integrates a Spring Boot backend with a MySQL database and a dynamic frontend using Fetch API.

---

## Technologies Used
- Backend: Spring Boot, Spring Data JPA, Hibernate
- Database: MySQL (XAMPP)
- Frontend: HTML, CSS, JavaScript (Fetch API)

---

## Database Schema

### Product Table
![1-ecommerce.png](Lab8_testing/1-ecommerce.png)
### Category Table 
![category table.png](Lab8_testing/category%20table.png)
### Relationship
- One Category has many Products (One-to-Many)

---

## API Endpoints

| Method | Endpoint | Description |
|-------|--------|-------------|
| GET | /api/v1/products | Get all products |
| POST | /api/v1/products | Create product |
| PUT | /api/v1/products/{id} | Update product |
| DELETE | /api/v1/products/{id} | Delete product |

---

## Frontend Integration

The frontend uses Fetch API to dynamically retrieve product data:

async function fetchProducts() {

    try {

        const response = await fetch("http://localhost:8080/api/v1/products");

        // Check response status
        if (!response.ok) {

            if (response.status === 404) {
                throw new Error("Products not found");
            }

            if (response.status === 500) {
                throw new Error("Internal server error");
            }

            throw new Error("Failed to fetch products");
        }

        products = await response.json();

        renderProducts(products);

    } catch (error) {

        console.error("Fetch error:", error.message);

        const productContainer = document.querySelector(".products-grid");

        if (productContainer) {
            productContainer.innerHTML = `
                <h2>${error.message}</h2>
            `;
        }
    }
}

function renderProducts(products) {

    const productContainer = document.querySelector(".products-grid");

    if (!productContainer) return;

    productContainer.innerHTML = "";

    // Empty state
    if (products.length === 0) {

        productContainer.innerHTML = `
            <h2>No products available</h2>
        `;

        return;
    }

    products.forEach(product => {

        const card = document.createElement("article");

        const title = document.createElement("h3");
        title.textContent = product.name;

        const img = document.createElement("img");

        console.log("PRODUCT:',product");
        console.log("IMAGE VALUE:", product.image_url)
        img.src = "images/Minimal-Watch-1.jpg"
        img.alt = product.name;
       

        const price = document.createElement("p");
        price.textContent = "₱" + product.price;
        price.classList.add("price");

        const button = document.createElement("button");
        button.textContent = "Add to Cart";
        button.setAttribute("data-id", product.id);

        card.appendChild(title);
        card.appendChild(img);
        card.appendChild(price);
        card.appendChild(button);

        productContainer.appendChild(card);
    });
}

## Testing

###  Flow Test

* Products are fetched from backend
* Displayed dynamically on page
* The layout adjusts properly for mobile and desktop views.
* ![task8.png](Lab8_testing/task8.png)

###  Persistence Test

* Data remains after server restart

###  Console Check

* No CORS errors
* No fetch errors
* ![check cors.png](Lab8_testing/check%20cors.png)



---

## Database 
*Updated Product Table after Testing in Postman
![TASK_UPDATTEDTABLE_AFTERTEST.png](Lab8_testing/TASK_UPDATTEDTABLE_AFTERTEST.png)

---
## Some Testing in Postman

## For Task 3:

Product Table

![task3_productTable.png](Lab8_testing/task3_productTable.png)

POST method for categories
![Task3_PostTESTING.png](Lab8_testing/Task3_PostTESTING.png)

POST method for getting products
![task3_post_testonly.png](Lab8_testing/task3_post_testonly.png)

GET method for getiing categories
![Task3_getcategories.png](Lab8_testing/Task3_getcategories.png)

----


## For Task 4:

POST
![tASK4.png](Lab8_testing/tASK4.png)

GET PRODUCTS
![Task4_get.png](Lab8_testing/Task4_get.png)

GET AN INVALID ID
![Task4_GETinvlaid_ID.png](Lab8_testing/Task4_GETinvlaid_ID.png)

GET VALID ID
![Task4_GETvalidID.png](Lab8_testing/Task4_GETvalidID.png)

PATCH VALID ID
![Task4_PATCHpartialupdate.png](Lab8_testing/Task4_PATCHpartialupdate.png)

PUT VALID ID
![Task4_PUTinvalidBODY.png](Lab8_testing/Task4_PUTinvalidBODY.png)

SUCCESSFULLY ADDED IN DATABASE
![Task4_PutTABLEsuccess.png](Lab8_testing/Task4_PutTABLEsuccess.png)

FOR FILTERING
![Task4_Filter.png](Lab8_testing/Task4_Filter.png)

FOR DELETING
![tASK4_dELETE.png](Lab8_testing/tASK4_dELETE.png)


## Author

* Khiara Espelimbergo
* Jhoban Aldrin Fortuna
