package org.softwarecraftsmanship.gilded.mall;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GildedStockManagerShould {

    private GildedStockManager shop;

    @Mock
    GildedClock clock = mock(GildedClock.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        shop = GildedStockManagerFactory.create();
    }

    @Test
    public void be_initially_empty() {
        assertThat(shop.stockList()).isEmpty();
    }

    @Test
    public void list_items_added_to_it() {
        TimestampedItem item = new BasicItem("some item", ONE, LocalDate.of(2018, 1, 1));

        shop.addItem(item);

        assertThat(shop.stockList()).containsExactly(new BasicItem("some item", ONE, LocalDate.of(2018, 1, 1)));
    }

    @Test
    public void reduce_Gilded_Dress_items_by_a_quarter_after_10_weeks() {
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
    public void remove_expired_tin_can_items_from_stock_list() {
        GildedStockAdapter<GildedTinCanItem> shop = GildedStockManagerFactory.createForGildedTinCanItem(clock);

        LocalDate insertionDate = LocalDate.of(2018, 1, 1);

        BigDecimal originalPrice = BigDecimal.valueOf(4);
        LocalDate expiryDate = LocalDate.of(2019, 1, 1);
        GildedTinCanItem tinCanItem = new GildedTinCanItem("Gilded tin", originalPrice, insertionDate, expiryDate);

        shop.addItem(tinCanItem);

        when(clock.today()).thenReturn(LocalDate.of(2019, 2, 2));

        assertEquals(0, shop.stockList().size());
    }

    @Test
    public void remove_carrot_items_after_seven_days() {
        GildedStockAdapter<GildedCarrotItem> shop = GildedStockManagerFactory.createForGildedCarrot(clock);

        LocalDate insertionDate = LocalDate.of(2018, 1, 1);

        BigDecimal price = BigDecimal.valueOf(1);
        GildedCarrotItem carrotItem = new GildedCarrotItem("Gilded carrot", price, insertionDate);
        shop.addItem(carrotItem);

        when(clock.today()).thenReturn(insertionDate.plusWeeks(1));

        assertEquals(0, shop.stockList().size());
    }
}
