package org.softwarecraftsmanship.gilded.mall;

import org.junit.Before;
import org.junit.Test;
import org.softwarecraftsmanship.gilded.mall.GildedStockManager.StockItem;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;

public class GildedStockManagerShould {

    private GildedStockManager shop;

    @Before
    public void setUp() {
        shop = GildedStockManagerFactory.create();
    }

    @Test
    public void be_initially_empty() {
        assertThat(shop.stockList()).isEmpty();
    }

    @Test
    public void list_items_added_to_it() {
        StockItem item = item("some item", ONE);

        shop.addItem(item);

        assertThat(shop.stockList()).containsExactly(item("some item", ONE));
    }

    private StockItem item(String name, BigDecimal price) {
        return new StockItem(name, price);
    }


}
