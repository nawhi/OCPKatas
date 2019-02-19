package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GildedStockAdapter<T extends TimestampedItem> {

    private GildedStockManager stockManager;
    private GildedClock clock;

    public GildedStockAdapter(GildedStockManager stockManager, GildedClock clock) {
        this.stockManager = stockManager;
        this.clock = clock;
    }

    public List<TimestampedItem> stockList() {
        return stockAt(clock.today())
                .collect(Collectors.toList());
    }

    private Stream<TimestampedItem> stockAt(LocalDate date) {
        return stockManager.stockList()
                .stream()
                .filter(i -> i.isSellable(date));
    }

    public void addItem(T item) {
        stockManager.addItem(item);
    }

    public Report stockReport() {
        LocalDate today = clock.today();
        BigDecimal originalValue = valueAt(today.minusDays(30));
        BigDecimal currentValue = valueAt(today);

        return new Report(currentValue, originalValue.subtract(currentValue));
    }

    private BigDecimal valueAt(LocalDate date) {
        return stockAt(date)
                .map(item -> item.getPrice(date))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
