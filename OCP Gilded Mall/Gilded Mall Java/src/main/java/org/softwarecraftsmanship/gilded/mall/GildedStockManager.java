package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

public class GildedStockManager {

    private List<TimestampedItem> stockList = new ArrayList<>();
    private GildedClock clock;

    public GildedStockManager(GildedClock clock) {
        this.clock = clock;
    }

    public GildedStockManager() {}

    public List<TimestampedItem> stockList() {
        return stockList;
    }

    public void addItem(TimestampedItem item) {
        stockList.add(item);
    }

    public static class StockItem {
        private String name;
        private BigDecimal price;

        @Override
        public boolean equals(Object o) {
            return o instanceof StockItem
                    && ((StockItem) o).name.equals(this.name)
                    && ((StockItem) o).price.equals(this.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }

        public StockItem(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
