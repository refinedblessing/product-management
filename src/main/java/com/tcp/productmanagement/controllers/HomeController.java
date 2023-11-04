package com.tcp.productmanagement.controllers;

import com.tcp.productmanagement.entities.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Home")
public class HomeController {
    @Operation(summary = "Health check page")
    @GetMapping
    public ResponseEntity<String> getProducts() {
        return new ResponseEntity<>("Welcome home", HttpStatus.OK);
    }
}
