package org.softwarecraftsmanship.gilded.mall;

import java.time.LocalDate;

public class GildedStockManagerFactory {

    public static GildedStockManager create() {
        return new GildedStockManager();
    }

    public static GildedStockAdapter<GildedDressItem> createForGildedDress() {
        return new GildedStockAdapter<>(new GildedStockManager(), LocalDate::now);
    }

    public static GildedStockAdapter<GildedTinCanItem> createForGildedTinCanItem(GildedClock clock) {
        return new GildedStockAdapter<>(new GildedStockManager(), clock);
    }
}
