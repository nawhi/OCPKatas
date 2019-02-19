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

public class GildedCarrotShopShould {

    @Mock
    GildedClock clock = mock(GildedClock.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void remove_carrot_items_after_seven_days() {
        GildedStockAdapter<GildedCarrotItem> shop = GildedStockManagerFactory.carrot(clock);

        LocalDate insertionDate = LocalDate.of(2018, 1, 1);

        BigDecimal price = BigDecimal.valueOf(1);
        GildedCarrotItem carrotItem = new GildedCarrotItem("Gilded carrot", price, insertionDate);
        shop.addItem(carrotItem);

        when(clock.today()).thenReturn(insertionDate.plusWeeks(1));

        assertEquals(0, shop.stockList().size());
    }
}
