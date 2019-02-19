package org.softwarecraftsmanship.gilded.mall;

public class GildedStockManagerFactory {

    public static GildedStockManager create() {
        return new GildedStockManager();
    }

    public static GildedStockAdapter dressShop(GildedClock now) {
        return new GildedStockAdapter(new GildedStockManager(), now);
    }

    public static GildedStockAdapter tinCanShop(GildedClock clock) {
        return new GildedStockAdapter(new GildedStockManager(), clock);
    }

    public static GildedStockAdapter carrotShop(GildedClock clock) {
        return new GildedStockAdapter(new GildedStockManager(), clock);
    }
}
