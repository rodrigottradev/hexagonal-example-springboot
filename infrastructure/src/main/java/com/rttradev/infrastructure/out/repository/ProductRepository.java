package com.rttradev.infrastructure.out.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rttradev.infrastructure.out.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
