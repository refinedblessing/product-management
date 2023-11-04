package com.tcp.productmanagement.controllers;

import com.tcp.productmanagement.entities.Product;
import com.tcp.productmanagement.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//@RestController
//@RequestMapping("/populate")
public class DataController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> populateDatabaseWithTestData() {
        try {
            List<Product> products = Arrays.asList(
                    new Product("Apple iPhone 13", LocalDate.parse("2023-12-01"), LocalDate.parse("2023-11-25"), 5, 15, 50),
                    new Product("Samsung Galaxy S22", LocalDate.parse("2023-12-10"), LocalDate.parse("2023-12-04"), 5, 3, 100),
                    new Product("Dell XPS 13 Laptop", LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-09"), 2, 10, 30),
                    new Product("Sony PlayStation 5", LocalDate.parse("2023-10-25"), LocalDate.parse("2023-10-19"), 3, 5, 20),
                    new Product("Nike Air Max Shoes", LocalDate.parse("2023-10-31"), LocalDate.parse("2023-10-25"), 10, 50, 15),
                    new Product("Apple iPad Pro", LocalDate.parse("2023-11-30"), LocalDate.parse("2023-11-24"), 5, 1, 40),
                    new Product("LG 65-Inch 4K TV", LocalDate.parse("2023-12-05"), LocalDate.parse("2023-11-29"), 5, 20, 60),
                    new Product("ASUS ROG Gaming Laptop", LocalDate.parse("2023-10-20"), LocalDate.parse("2023-10-14"), 5, 8, 25),
                    new Product("Canon EOS Rebel DSLR", LocalDate.parse("2023-12-15"), LocalDate.parse("2023-12-09"), 3, 7, 18),
                    new Product("Nike Zoom Pegasus Running Shoes", LocalDate.parse("2023-11-08"), LocalDate.parse("2023-11-02"), 8, 1, 35),
                    new Product("Apple Watch Series 7", LocalDate.parse("2023-10-10"), LocalDate.parse("2023-10-04"), 5, 10, 28),
                    new Product("LG Microwave Oven", LocalDate.parse("2023-11-05"), LocalDate.parse("2023-10-30"), 4, 9, 22),
                    new Product("Sony 65-Inch 8K TV", LocalDate.parse("2023-12-07"), LocalDate.parse("2023-12-01"), 5, 1, 38)
            );

            productRepository.saveAll(products);

            return new ResponseEntity<>("Database populated with test data.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
