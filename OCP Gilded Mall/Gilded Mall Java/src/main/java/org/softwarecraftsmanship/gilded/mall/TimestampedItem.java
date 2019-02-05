package org.softwarecraftsmanship.gilded.mall;

import org.softwarecraftsmanship.gilded.mall.GildedStockManager.StockItem;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TimestampedItem extends StockItem {

    private final LocalDate insertionDate;

    public TimestampedItem(String name, BigDecimal price, LocalDate insertionDate) {
        super(name, price);
        this.insertionDate = insertionDate;
    }

    protected LocalDate getInsertionDate() {
        return insertionDate;
    }

    abstract BigDecimal getPrice(LocalDate today);
}
