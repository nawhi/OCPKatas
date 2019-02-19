package org.softwarecraftsmanship.gilded.mall;

public class GildedStockManagerFactory {

    public static GildedStockManager create() {
        return new GildedStockManager();
    }

    public static GildedStockAdapter<GildedDressItem> dress(GildedClock now) {
        return new GildedStockAdapter<>(new GildedStockManager(), now);
    }

    public static GildedStockAdapter<GildedTinCanItem> tinCan(GildedClock clock) {
        return new GildedStockAdapter<>(new GildedStockManager(), clock);
    }

    public static GildedStockAdapter<GildedCarrotItem> carrot(GildedClock clock) {
        return new GildedStockAdapter<>(new GildedStockManager(), clock);
    }
}
