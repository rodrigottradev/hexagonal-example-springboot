package com.rttradev.infrastructure.out.repository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rttradev.infrastructure.out.entity.BrandEntity;
import com.rttradev.infrastructure.out.entity.PriceEntity;
import com.rttradev.infrastructure.out.entity.ProductEntity;

@Configuration
public class Population {

    @Value("classpath:prices.json")
    Resource pricesJson;

    @Bean
    CommandLineRunner commandLineRunner(PriceRepository repository, ProductRepository productRepository, BrandRepository brandRepository) {
        return args -> {
            ProductEntity productEntity = ProductEntity.builder()
                .id(UUID.fromString("c129db85-37c0-4f47-94d5-3274d8c598fa"))
                .build();
            BrandEntity brandEntity = BrandEntity.builder()
                .id(UUID.fromString("90d3f4a9-1fe1-4fb4-b09a-e563e21c4be3"))
                .name("TEST BRAND")
                .build();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            productRepository.save(productEntity);
            brandRepository.save(brandEntity);
            try {
                byte[] jsonData = pricesJson.getInputStream().readAllBytes();
                List<PriceEntity> priceEntities =
                    objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructCollectionType(List.class, PriceEntity.class));
                priceEntities.forEach(priceEntity -> {
                    priceEntity.setId(UUID.randomUUID());
                    priceEntity.setBrandEntity(brandEntity);
                    priceEntity.setProductEntity(productEntity);
                });

                repository.saveAll(
                    priceEntities
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
