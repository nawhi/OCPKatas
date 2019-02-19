package org.softwarecraftsmanship.gilded.mall;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GildedDressShopShould {

    @Mock
    GildedClock clock = mock(GildedClock.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void reduce_items_by_a_quarter_after_10_weeks() {
        GildedStockAdapter<GildedDressItem> shop = GildedStockManagerFactory.createForGildedDress();

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
        GildedStockAdapter<GildedDressItem> shop = GildedStockManagerFactory.createForGildedDress();

        BigDecimal originalPrice = BigDecimal.valueOf(4);
        LocalDate insertionDate = LocalDate.of(2018, 1, 1);
        GildedDressItem dress1 =
                new GildedDressItem("Gilded Dress", originalPrice, insertionDate);

        shop.addItem(dress1);

        Report actualReport = shop.getReport();

        when(clock.today()).thenReturn(insertionDate.plusDays(30));

        assertThat(actualReport).isEqualTo(new Report(BigDecimal.valueOf(4), BigDecimal.valueOf(0)));
    }
}
