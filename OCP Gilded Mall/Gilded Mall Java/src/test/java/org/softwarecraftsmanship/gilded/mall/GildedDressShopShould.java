package org.softwarecraftsmanship.gilded.mall;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedDressShopShould {

    @Test
    public void reduce_items_by_a_quarter_after_10_weeks() {
        GildedStockAdapter<GildedDressItem> shop = GildedStockManagerFactory.createForGildedDress(LocalDate::now);

        LocalDate insertionDate = LocalDate.of(2018, 1, 1);

        BigDecimal originalPrice = BigDecimal.valueOf(4);
        GildedDressItem dress = new GildedDressItem("Gilded Dress", originalPrice, insertionDate);

        shop.addItem(dress);

        TimestampedItem dressFromStockList = shop.stockList().get(0);

        BigDecimal reducedPrice = BigDecimal.valueOf(3);
        BigDecimal actualPrice = dressFromStockList.getPrice(insertionDate.plusWeeks(10));
        assertThat(actualPrice.compareTo(reducedPrice)).isEqualTo(0);
    }

    @Test
    public void get_monthly_report() {
        LocalDate insertionDate = LocalDate.of(2018, 1, 1);
        LocalDate reportDate = insertionDate.plusDays(30);
        GildedStockAdapter<GildedDressItem> shop = GildedStockManagerFactory.createForGildedDress(() -> reportDate);

        BigDecimal originalPrice = BigDecimal.valueOf(4);
        GildedDressItem dress1 =
                new GildedDressItem("Gilded Dress", originalPrice, insertionDate);

        shop.addItem(dress1);

        Report actualReport = shop.getReport();

        assertThat(actualReport).isEqualTo(new Report(BigDecimal.valueOf(4), BigDecimal.valueOf(0)));
    }

}
