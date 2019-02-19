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

    public List<T> stockList() {
        return stockManager.stockList().stream()
                .filter(i -> i.isSellable(clock.today()))
                .map(i -> (T) i)
                .collect(Collectors.toList());
    }

    public void addItem(T item) {
        stockManager.addItem(item);
    }

    public Report getReport() {
        BigDecimal stockValue = stockManager.stockList()
                .stream()
                .map(i-> i.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Report(stockValue, BigDecimal.valueOf(0));
    }
}
