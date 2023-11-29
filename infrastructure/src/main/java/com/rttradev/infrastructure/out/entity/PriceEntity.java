package com.rttradev.infrastructure.out.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    @JoinColumn(
        name = "fk_brand",
        nullable = false
    )
    private BrandEntity brandEntity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(
        name = "fk_product",
        nullable = false
    )
    private ProductEntity productEntity;
    private Integer priceList;
    private Integer priority;
    private Double price;
    private String currency;

}
