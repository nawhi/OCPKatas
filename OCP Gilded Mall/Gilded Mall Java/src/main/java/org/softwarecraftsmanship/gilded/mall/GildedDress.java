package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GildedDress extends TimestampedItem {
    public GildedDress(String name, BigDecimal price, LocalDate insertionDate) {
        super(name, price, insertionDate);
    }

    @Override
    BigDecimal getPrice(LocalDate today) {
        BigDecimal basePrice = super.getPrice();
        return olderthanTenWeeks(today)
                ? basePrice.multiply(BigDecimal.valueOf(0.75))
                : basePrice;
    }

    private boolean olderthanTenWeeks(LocalDate today) {
        return today.minusWeeks(10).compareTo(getInsertionDate()) <= 0;
    }
}
