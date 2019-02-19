package org.softwarecraftsmanship.gilded.mall;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GildedTinCanShopShould {

    @Mock
    GildedClock clock = mock(GildedClock.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void remove_expired_items_from_stock_list() {
        GildedStockAdapter<GildedTinCanItem> shop = GildedStockManagerFactory.createForGildedTinCanItem(clock);

        LocalDate insertionDate = LocalDate.of(2018, 1, 1);

        BigDecimal originalPrice = BigDecimal.valueOf(4);
        LocalDate expiryDate = LocalDate.of(2019, 1, 1);
        GildedTinCanItem tinCanItem = new GildedTinCanItem("Gilded tin", originalPrice, insertionDate, expiryDate);

        shop.addItem(tinCanItem);

        when(clock.today()).thenReturn(LocalDate.of(2019, 2, 2));

        assertEquals(0, shop.stockList().size());
    }
}
