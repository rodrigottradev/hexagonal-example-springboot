package com.rttradev.application.ports.out;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.rttradev.application.domain.Price;

public interface PriceOutputPort {

    Optional<Price> findHighestPriorityPriceByDateAndProductAndBrand(LocalDateTime date, UUID productId, UUID brandId);

}
