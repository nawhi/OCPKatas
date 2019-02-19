package org.softwarecraftsmanship.gilded.mall;

import org.softwarecraftsmanship.gilded.mall.GildedStockManager.StockItem;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TimestampedItem {

    private StockItem item;

    private final LocalDate insertionDate;

    public TimestampedItem(String name, BigDecimal price, LocalDate insertionDate) {
        item = new StockItem(name, price);
        this.insertionDate = insertionDate;
    }

    protected LocalDate getInsertionDate() {
        return insertionDate;
    }

    BigDecimal getPrice(LocalDate today) { return item.getPrice(); }

    boolean isSellable(LocalDate today) {
        return true;
    }
}
