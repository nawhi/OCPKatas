package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GildedCarrotItem extends TimestampedItem {
    public GildedCarrotItem(String name, BigDecimal price, LocalDate insertionDate) {
        super(name, price, insertionDate);
    }

    @Override
    BigDecimal getPrice(LocalDate today) {
        return super.getPrice(today);
    }

    @Override
    boolean isSellable(LocalDate today) {
        return today.minusWeeks(1).isBefore(getInsertionDate());
    }
}
