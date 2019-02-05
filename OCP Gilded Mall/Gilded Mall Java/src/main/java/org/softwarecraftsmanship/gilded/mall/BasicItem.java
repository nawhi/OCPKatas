package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BasicItem extends TimestampedItem {

    public BasicItem(String name, BigDecimal price, LocalDate insertionDate) {
        super(name, price, insertionDate);
    }

    @Override
    BigDecimal getPrice(LocalDate today) {
        return getPrice();
    }
}
