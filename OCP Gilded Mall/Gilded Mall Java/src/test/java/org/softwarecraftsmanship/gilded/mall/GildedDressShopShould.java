package org.softwarecraftsmanship.gilded.mall;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.softwarecraftsmanship.gilded.mall.GildedStockManagerFactory.createForGildedDress;

public class GildedDressShopShould {

    public static final LocalDate INSERTION_DATE = LocalDate.of(2018, 1, 1);

    @Test
    public void reduce_items_by_a_quarter_after_10_weeks() {
        LocalDate today = INSERTION_DATE.plusWeeks(10).plusDays(1);
        GildedStockAdapter<GildedDressItem> shop = createForGildedDress(() -> today);
        BigDecimal reducedPrice = BigDecimal.valueOf(3);

        shop.addItem(standardDress(BigDecimal.valueOf(4)));

        TimestampedItem dressFromStockList = shop.stockList().get(0);

        BigDecimal actualPrice = dressFromStockList.getPrice(today);
        assertThat(actualPrice.compareTo(reducedPrice)).isEqualTo(0);
    }

    @Test
    public void get_monthly_report() {
        LocalDate today = INSERTION_DATE.plusDays(1);
        GildedStockAdapter<GildedDressItem> shop = createForGildedDress(() -> today);

        shop.addItem(standardDress(BigDecimal.valueOf(4)));

        Report actualReport = shop.getReport();

        assertThat(actualReport).isEqualTo(new Report(4, 0));
    }

    private GildedDressItem standardDress(BigDecimal dressPrice) {
        return new GildedDressItem("Gilded Dress", dressPrice, INSERTION_DATE);
    }

}
