package org.softwarecraftsmanship.gilded.mall;

public class GildedStockManagerFactory {

    public static GildedStockManager create() {
        return new GildedStockManager();
    }

    public static GildedStockAdapter<GildedDressItem> createForGildedDress() {
        return new GildedStockAdapter<>(new GildedStockManager());
    }
}
