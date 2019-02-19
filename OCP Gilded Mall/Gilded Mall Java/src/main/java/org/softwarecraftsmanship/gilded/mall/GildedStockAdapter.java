package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class GildedStockAdapter<T extends TimestampedItem> {

    private GildedStockManager stockManager;
    private GildedClock clock;

    public GildedStockAdapter(GildedStockManager stockManager, GildedClock clock) {
        this.stockManager = stockManager;
        this.clock = clock;
    }

    public List<TimestampedItem> stockList() {
        return stockManager.stockList().stream()
                .filter(i -> i.isSellable(clock.today()))
                .collect(Collectors.toList());
    }

    public void addItem(T item) {
        stockManager.addItem(item);
    }

    public Report getReport() {
        BigDecimal originalStockValue = getOriginalStockValue();
        BigDecimal stockValue = getCurrentStockValue();

        return new Report(stockValue, originalStockValue.subtract(stockValue));
    }

    private BigDecimal getCurrentStockValue() {
        return stockManager.stockList()
                .stream()
                .map(i-> i.getPrice(clock.today()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getOriginalStockValue() {
        return stockManager.stockList()
                .stream()
                .map(i-> i.getPrice(i.getInsertionDate()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
