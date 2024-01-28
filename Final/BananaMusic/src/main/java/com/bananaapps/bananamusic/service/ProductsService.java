package com.bananaapps.bananamusic.service;

import com.bananaapps.bananamusic.domain.Product;
import com.bananaapps.bananamusic.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Product> getProductsByText(String text) {
        return productsRepository.findByNameContaining(text);
    }
}
