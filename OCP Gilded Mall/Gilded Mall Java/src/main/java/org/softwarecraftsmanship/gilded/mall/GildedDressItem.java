package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GildedDressItem extends TimestampedItem {
    public GildedDressItem(String name, BigDecimal price, LocalDate insertionDate) {
        super(name, price, insertionDate);
    }

    @Override
    BigDecimal getPrice(LocalDate today) {
        BigDecimal basePrice = super.getPrice(today);
        return olderthanTenWeeks(today)
                ? basePrice.multiply(BigDecimal.valueOf(0.75))
                : basePrice;
    }

    private boolean olderthanTenWeeks(LocalDate today) {
        return getInsertionDate().plusWeeks(10).isBefore(today);
    }
}
