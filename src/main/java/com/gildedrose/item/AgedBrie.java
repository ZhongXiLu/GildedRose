package com.gildedrose.item;

import com.gildedrose.Item;

public class AgedBrie extends GildedRoseItem {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        setQuality(item.quality + 1);
    }

}
