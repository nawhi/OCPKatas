package org.softwarecraftsmanship.gilded.mall;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.softwarecraftsmanship.gilded.mall.GildedStockManagerFactory.tinCan;

public class GildedTinCanShopShould {

    private static final LocalDate INSERTION_DATE = LocalDate.of(2018, 1, 1);
    public static final LocalDate EXPIRY_DATE = INSERTION_DATE.plusYears(1);

    @Test
    public void remove_expired_items_from_stock_list() {
        GildedStockAdapter shop = tinCan(() -> EXPIRY_DATE.plusDays(1));

        shop.addItem(standardItem(BigDecimal.valueOf(4)));

        assertEquals(0, shop.stockList().size());
    }

    @Ignore
    @Test
    public void generate_report_with_loss_from_expired_items() {

        GildedStockAdapter shop = tinCan(() -> EXPIRY_DATE.plusDays(1));

        shop.addItem(standardItem(BigDecimal.valueOf(4)));

        assertEquals(new Report(0, 4), shop.stockReport());

    }

    private GildedTinCanItem standardItem(BigDecimal itemPrice) {
        return new GildedTinCanItem("Gilded tin", itemPrice, INSERTION_DATE, EXPIRY_DATE);
    }
}
