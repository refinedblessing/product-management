# Product Management Application

This is a Product Management application that allows you to manage and monitor product information, including product details, expiration dates, markdown information, and replenishment needs. The application provides a RESTful API to perform various operations on products.
It is currently hosted on AWS Elastic Beanstalk 👀 [here](http://safe-shelving.us-west-2.elasticbeanstalk.com/swagger-ui/index.html#/) 👀 using Swagger UI for the GUI.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)


## Installation

1. Clone the repository to your local machine.
    ```shell
       git clone https://github.com/refinedblessing/product-management
    ```

2. Configure your database settings in application.properties 
3. Build and run the application using Maven:
    ```shell
    mvn clean install
    mvn spring-boot:run
    ```

### Usage

The Product Management Application provides RESTful API endpoints for various product-related operations. Below are the available API endpoints and their descriptions:
It is currently hosted 👀 [here](http://safe-shelving.us-west-2.elasticbeanstalk.com/swagger-ui/index.html#/) 👀 using Swagger UI for the GUI.


#### API Endpoints

##### Get a list of all products
- Endpoint: /api/products
- Method: GET
- Description: Retrieve a list of all products.

##### Get a product by ID
- Endpoint: /api/products/{id}
- Method: GET
- Description: Retrieve a product by its ID.

##### Add a new product
- Endpoint: /api/products
- Method: POST
- Description: Add a new product.

##### Update an existing product
- Endpoint: /api/products/{id}
- Method: PUT
- Description: Update an existing product by its ID.

##### Delete a product
- Endpoint: /api/products/{id}
- Method: DELETE
- Description: Delete a product by its ID.

#### Additional Routes (Project-Specific Requirements):

##### Create Product: ProductName, optional (ExpiryDate, markdownDate, Quantity, minThreshold, maxThreshold)
- Endpoint: /api/products/create-product
- Method: POST
- Description: Create a new product with optional details.

##### Display Product: ProductName (optional), ProductID (optional)
- Endpoint: /api/products/display-product
- Method: GET
- Description: Display a product by name or ID, or retrieve all products.

##### Display Product To Refill: none, ProductID (optional)
- Endpoint: /api/products/display-product-to-refill
- Method: GET
- Description: Display products that need replenishment.

##### Display Product Count: none, ProductID (optional)
- Endpoint: /api/products/display-product-count
- Method: GET
- Description: Display the count of products.

##### Display Products Expiry Date: none, ProductID (optional)
- Endpoint: /api/products/display-products-expiry-date
- Method: GET
- Description: Display product expiry dates.

##### Display Expired Products: none
- Endpoint: /api/products/display-expired-products
- Method: GET
- Description: Display expired products.

##### Display Products In Markdown: none
- Endpoint: /api/products/display-products-in-markdown
- Method: GET
- Description: Display products currently in markdown.

##### Display Products For Markdown: none
- Endpoint: /api/products/display-products-for-markdown
- Method: GET
- Description: Display products that need markdown.