package com.tcp.productmanagement.controllers;

import com.tcp.productmanagement.entities.Product;
import com.tcp.productmanagement.exceptions.EntityNotFoundException;
import com.tcp.productmanagement.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get a list of all products ")
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Get a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a new product")
    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    ROUTES BELOW HERE ARE SPECIFIC REQUIREMENTS FROM A PROJECT SPECIFICATION
    @Operation(summary = "`createProduct: ProductName, optional (ExpiryDate, markdownDate, Quantity, minThreshold, maxThreshold)`")
    @PostMapping("/create-product")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @Operation(summary = "`displayProduct: ProductName(optional), ProductID(optional).`")
    @GetMapping("/display-product")
    public ResponseEntity<List<Product>> displayProduct(@RequestParam(required = false) String name, @RequestParam(required = false) Long id) {
        if (name != null) {
            Product product = productService.getProductByName(name);
            return new ResponseEntity<>(List.of(product), HttpStatus.OK);
        } else if (id != null) {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(List.of(product), HttpStatus.OK);
        }

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "`displayProductToRefill: none, ProductID(optional).`")
    @GetMapping("/display-product-to-refill")
    public List<String> displayProductToRefill(@RequestParam(required = false) Long id) {
        if (id != null) {
            return List.of(productService.getReplenishmentInfo(id));
        }
        return productService.getReplenishmentInfoForAllProducts();
    }

    @Operation(summary = "`displayProductCount: none, ProductID(optional).`")
    @GetMapping("/display-product-count")
    public List<String> displayProductCount(@RequestParam(required = false) Long id) {
        if (id != null) {
            Product product = productService.getProductById(id);
            return List.of(productService.getCountInfo(product));
        }
        return productService.getCountInfoForAllProducts();
    }

    @Operation(summary = "`displayProductsExpiryDate: none, ProductID(optional).`")
    @GetMapping("/display-products-expiry-date")
    public List<String> displayProductsExpiryDate(@RequestParam(required = false) Long id) {
        if (id != null) {
            Product product = productService.getProductById(id);
            return List.of(productService.getExpiryDateInfo(product));
        }
        return productService.getExpiryDateInfoForAllProducts();
    }

    @Operation(summary = "`displayExpiredProducts: none`")
    @GetMapping("/display-expired-products")
    public List<Product> displayExpiredProducts() {
        return productService.getAllExpiredProductInfo();
    }

    @Operation(summary = "`displayProductsInMarkdown:none`")
    @GetMapping("/display-products-in-markdown")
    public List<Product> displayProductsInMarkdown() {
        return productService.getAllProductsInMarkdownInfo();
    }

    @Operation(summary = "`displayProductsForMarkdown:none`")
    @GetMapping("/display-products-for-markdown")
    public List<Product> displayProductsForMarkdown() {
        return productService.getAllProductsForMarkdownInfo();
    }
}
