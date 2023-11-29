package com.rttradev.infrastructure.out.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rttradev.infrastructure.out.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM price_entity "
        + "WHERE start_date <= :date AND end_date >= :date AND fk_product = :productId AND fk_brand = :brandId "
        + "ORDER BY priority DESC LIMIT 1")
    Optional<PriceEntity> findHighestPriorityPriceByDateAndProductAndBrand(@Param("date") LocalDateTime date,
                                                                           @Param("productId") UUID productId,
                                                                           @Param("brandId") UUID brandId);
}
