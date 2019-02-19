package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GildedTinCanItem extends TimestampedItem{
    private LocalDate sellByDate;

    public GildedTinCanItem(String name, BigDecimal price, LocalDate insertionDate, LocalDate sellByDate) {
        super(name, price, insertionDate);
        this.sellByDate = sellByDate;
    }

    @Override
    boolean isSellable(LocalDate today) {
        return today.isBefore(sellByDate);
    }
}
