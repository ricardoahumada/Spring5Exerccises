package com.bananaapps.bananamusic.persistence;

import com.bananaapps.bananamusic.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    @Query("select p from Product p where p.name = ?1")
    Product findByName(String name);
}
