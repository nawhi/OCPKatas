package org.softwarecraftsmanship.gilded.mall;

public class GildedStockManagerFactory {

    public static GildedStockManager create() {
        return new GildedStockManager();
    }

    public static GildedStockAdapter dress(GildedClock now) {
        return new GildedStockAdapter(new GildedStockManager(), now);
    }

    public static GildedStockAdapter tinCan(GildedClock clock) {
        return new GildedStockAdapter(new GildedStockManager(), clock);
    }

    public static GildedStockAdapter carrot(GildedClock clock) {
        return new GildedStockAdapter(new GildedStockManager(), clock);
    }
}
