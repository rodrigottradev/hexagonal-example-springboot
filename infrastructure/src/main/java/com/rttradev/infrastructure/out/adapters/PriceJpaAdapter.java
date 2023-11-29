package com.rttradev.infrastructure.out.adapters;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rttradev.application.domain.Price;
import com.rttradev.application.ports.out.PriceOutputPort;
import com.rttradev.infrastructure.out.mapper.PriceMapper;
import com.rttradev.infrastructure.out.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PriceJpaAdapter implements PriceOutputPort {

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> findHighestPriorityPriceByDateAndProductAndBrand(final LocalDateTime date, final UUID productId, final UUID brandId) {
        return priceRepository.findHighestPriorityPriceByDateAndProductAndBrand(date, productId, brandId).stream().map(PriceMapper::toDomain)
            .findFirst();
    }

}
