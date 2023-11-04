package com.tcp.productmanagement.services;

import com.tcp.productmanagement.entities.Product;
import com.tcp.productmanagement.exceptions.EntityDuplicateException;
import com.tcp.productmanagement.exceptions.EntityNotFoundException;
import com.tcp.productmanagement.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found."));
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Product with name: " + name + " not found."));
    }

    @Override
    public Product addProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityDuplicateException("ProductName should have a uniqueID, the ProductName already exists with the same uniqueID");
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<String> getReplenishmentInfoForAllProducts() {
        List<Product> products = getAllProducts();
        return products.stream().map(this::getReplenishmentInfo).collect(Collectors.toList());
    }

    @Override
    public String getReplenishmentInfo(Product product) {
        Integer replenishmentQty = calculateReplenishmentQuantity(product);

        if (replenishmentQty > 0) {
            return "Replenish " + replenishmentQty + " " + product.getName();
        } else if (replenishmentQty == 0) {
            return "No replenishment needed for " + product.getName();
        } else {
            return "Remove " + Math.abs(replenishmentQty) + " " + product.getName();
        }
    }

    @Override
    public String getReplenishmentInfo(Long id) {
        Product product = getProductById(id);
        Integer replenishmentQty = calculateReplenishmentQuantity(product);

        if (replenishmentQty > 0) {
            return "Replenish " + replenishmentQty + " " + product.getName();
        } else if (replenishmentQty == 0) {
            return "No replenishment needed for " + product.getName();
        } else {
            return "Remove " + Math.abs(replenishmentQty) + " " + product.getName();
        }
    }

    @Override
    public Integer calculateReplenishmentQuantity(Product product) {
        int currentQuantity = product.getQuantity();
        int minThreshold = product.getMinThreshold();
        int maxThreshold = product.getMaxThreshold();

        if (currentQuantity < minThreshold) {
            // If current quantity is below the minimum threshold, calculate the replenishment quantity
            return minThreshold - currentQuantity;
        } else if (currentQuantity < maxThreshold) {
            // If current quantity is within the thresholds, no replenishment is needed
            return 0;
        } else {
            return maxThreshold - currentQuantity;
        }
    }

    @Override
    public String getCountInfo(Product product) {
        Integer count = product.getQuantity();
        return "We have " + count + " " + product.getName() + " in stock.";
    }

    @Override
    public List<String> getCountInfoForAllProducts() {
        List<Product> products = getAllProducts();
        return products.stream().map(this::getCountInfo).collect(Collectors.toList());
    }

    @Override
    public String getExpiryDateInfo(Product product) {
        String date = product.getExpiryDate().toString();
        return product.getName() + " expires " + date;
    }

    @Override
    public List<String> getExpiryDateInfoForAllProducts() {
        List<Product> products = getAllProducts();
        return products.stream().map(this::getExpiryDateInfo).collect(Collectors.toList());
    }

    @Override
    public Product getExpiredProductInfo(Product product) {
        LocalDate today = LocalDate.now();
        if (product.getExpiryDate().isBefore(today)) {
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getAllExpiredProductInfo() {
        List<Product> products = getAllProducts();
        return products.stream().map(this::getExpiredProductInfo).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Product getProductInMarkdownInfo(Product product) {
        LocalDate today = LocalDate.now();
        if (product.getMarkdownDate().isBefore(today)) {
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getAllProductsInMarkdownInfo() {
        List<Product> products = getAllProducts();
        return products.stream().map(this::getProductInMarkdownInfo).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Product getProductForMarkdownInfo(Product product) {
        LocalDate today = LocalDate.now();
        if (product.getMarkdownDate().isAfter(today.plusWeeks(1))) {
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getAllProductsForMarkdownInfo() {
        List<Product> products = getAllProducts();
        return products.stream().map(this::getProductForMarkdownInfo).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
