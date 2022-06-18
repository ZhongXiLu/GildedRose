package com.gildedrose.item;

import com.gildedrose.Item;

public class Sulfuras extends GildedRoseItem {

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        // Sulfuras never decreases in quality
    }

}
