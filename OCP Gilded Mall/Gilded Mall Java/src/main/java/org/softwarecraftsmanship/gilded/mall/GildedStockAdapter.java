package org.softwarecraftsmanship.gilded.mall;

import java.util.List;
import java.util.stream.Collectors;

public class GildedStockAdapter<T extends TimestampedItem> {

    private GildedStockManager stockManager;

    public GildedStockAdapter(GildedStockManager stockManager) {
        this.stockManager = stockManager;
    }

    public List<T> stockList() {
        return stockManager.stockList().stream()
                .map(i -> (T) i)
                .collect(Collectors.toList());
    }

    public void addItem(T item) {
        stockManager.addItem(item);
    }

}
