package com.rttradev.infrastructure.out.mapper;

import com.rttradev.application.domain.Product;
import com.rttradev.infrastructure.out.entity.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static Product toDomain(ProductEntity brandEntity) {
        return Product.builder()
            .id(brandEntity.getId())
            .build();
    }

    public static ProductEntity fromDomain(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .build();
    }

}
