package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePasses extends GildedRoseItem {

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        if (item.sellIn == 0) {
            setQuality(0);
        } else if (item.sellIn <= 5) {
            setQuality(item.quality + 3);
        } else if (item.sellIn <= 10) {
            setQuality(item.quality + 2);
        } else {
            setQuality(item.quality + 1);
        }
    }

}
