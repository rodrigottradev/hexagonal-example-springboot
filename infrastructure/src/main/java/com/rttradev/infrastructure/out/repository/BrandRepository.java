package com.rttradev.infrastructure.out.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rttradev.infrastructure.out.entity.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
}
