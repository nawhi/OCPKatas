package org.softwarecraftsmanship.gilded.mall;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.softwarecraftsmanship.gilded.mall.GildedStockManagerFactory.dressShop;

public class GildedDressShopShould {

    private static final LocalDate INSERTION_DATE = LocalDate.of(2018, 1, 1);

    @Test
    public void reduce_items_by_a_quarter_after_10_weeks() {
        LocalDate today = INSERTION_DATE.plusWeeks(10).plusDays(1);
        GildedStockAdapter shop = dressShop(() -> today);

        BigDecimal originalPrice = BigDecimal.valueOf(4);
        BigDecimal reducedPrice = BigDecimal.valueOf(3);

        shop.addItem(standardDress(originalPrice));

        BigDecimal actualPrice = shop.stockList().get(0).getPrice(today);
        assertThat(actualPrice, comparesEqualTo(reducedPrice));
    }

    @Test
    public void get_monthly_report() {
        LocalDate today = INSERTION_DATE.plusDays(1);
        GildedStockAdapter shop = dressShop(() -> today);

        shop.addItem(standardDress(BigDecimal.valueOf(4)));

        Report actualReport = shop.stockReport();

        assertThat(actualReport, is(new Report(4, 0)));
    }

    @Test
    public void get_monthly_report_with_depreciation_in_last_30_days() {
        LocalDate today = INSERTION_DATE.plusWeeks(10).plusDays(1);
        GildedStockAdapter shop = dressShop(() -> today);

        shop.addItem(standardDress(BigDecimal.valueOf(4)));

        Report actualReport = shop.stockReport();

        assertThat(actualReport, is(new Report(3, 1)));
    }





    private GildedDressItem standardDress(BigDecimal dressPrice) {
        return new GildedDressItem("Gilded Dress", dressPrice, INSERTION_DATE);
    }

}
