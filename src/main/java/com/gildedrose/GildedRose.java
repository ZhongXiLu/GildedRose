package com.gildedrose;

import com.gildedrose.item.AgedBrie;
import com.gildedrose.item.BackstagePasses;
import com.gildedrose.item.GildedRoseItem;
import com.gildedrose.item.Sulfuras;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    Item[] items;
    private final List<GildedRoseItem> gildedRoseItems = new ArrayList<>();

    public GildedRose(Item[] items) {
        this.items = items;
        for (Item item : items) {
            switch (item.name) {
                case SpecialItemNames.AGED_BRIE:
                    gildedRoseItems.add(new AgedBrie(item));
                    break;
                case SpecialItemNames.SULFURAS:
                    gildedRoseItems.add(new Sulfuras(item));
                    break;
                case SpecialItemNames.BACKSTAGE_PASSES:
                    gildedRoseItems.add(new BackstagePasses(item));
                    break;
                default:
                    boolean conjured = item.name.startsWith(SpecialItemNames.CONJURED);
                    gildedRoseItems.add(new GildedRoseItem(item, conjured));
            }
        }
    }

    public void updateQuality() {
        gildedRoseItems.forEach(GildedRoseItem::age);
    }
}
