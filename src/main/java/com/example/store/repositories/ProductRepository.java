package com.example.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
