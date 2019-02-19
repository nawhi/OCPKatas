package org.softwarecraftsmanship.gilded.mall;

public class GildedStockManagerFactory {

    public static GildedStockManager create() {
        return new GildedStockManager();
    }

    public static GildedStockAdapter<GildedDressItem> createForGildedDress(GildedClock now) {
        return new GildedStockAdapter<>(new GildedStockManager(), now);
    }

    public static GildedStockAdapter<GildedTinCanItem> createForGildedTinCanItem(GildedClock clock) {
        return new GildedStockAdapter<>(new GildedStockManager(), clock);
    }

    public static GildedStockAdapter<GildedCarrotItem> createForGildedCarrot(GildedClock clock) {
        return new GildedStockAdapter<>(new GildedStockManager(), clock);
    }
}
