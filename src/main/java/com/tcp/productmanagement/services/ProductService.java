package com.tcp.productmanagement.services;

import com.tcp.productmanagement.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);

    Product getProductByName(String name);

    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

    List<String> getReplenishmentInfoForAllProducts();

    String getReplenishmentInfo(Product product);

    String getReplenishmentInfo(Long id);

    Integer calculateReplenishmentQuantity(Product product);

    String getCountInfo(Product product);

    List<String> getCountInfoForAllProducts();

    String getExpiryDateInfo(Product product);

    List<String> getExpiryDateInfoForAllProducts();

    Product getExpiredProductInfo(Product product);

    List<Product> getAllExpiredProductInfo();

    Product getProductInMarkdownInfo(Product product);

    List<Product> getAllProductsInMarkdownInfo();

    Product getProductForMarkdownInfo(Product product);

    List<Product> getAllProductsForMarkdownInfo();
}
