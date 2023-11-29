package com.rttradev.application.adapters;

import com.rttradev.application.config.UseCase;
import com.rttradev.application.domain.Price;
import com.rttradev.application.domain.PriceSearchRequest;
import com.rttradev.application.error.ErrorDictionary;
import com.rttradev.application.error.PriceNotFoundError;
import com.rttradev.application.ports.in.PriceInputPort;
import com.rttradev.application.ports.out.PriceOutputPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SearchPriceUseCase implements PriceInputPort {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price searchPrice(final PriceSearchRequest priceSearchRequest) throws PriceNotFoundError {
        return priceOutputPort.findHighestPriorityPriceByDateAndProductAndBrand(priceSearchRequest.getDate(), priceSearchRequest.getProductId(),
                priceSearchRequest.getBrandId())
            .orElseThrow(() -> new PriceNotFoundError(ErrorDictionary.PRICE_NOT_FOUND, 404));
    }

}
