package com.gildedrose.item;

import com.gildedrose.Item;

public class GildedRoseItem {

    protected final Item item;
    protected int qualityDegradeRate = 1;

    public GildedRoseItem(Item item) {
        this(item, false);
    }

    public GildedRoseItem(Item item, boolean conjured) {
        this.item = item;
        if (conjured) {
            qualityDegradeRate *= 2;
        }
    }


    public void age() {
        updateQuality();
        updateSellIn();
    }

    protected void setQuality(int quality) {
        if (quality >= 0 && quality <= 50) {
            item.quality = quality;
        }
    }

    protected void setSellIn(int sellIn) {
        if (sellIn >= 0) {
            item.sellIn = sellIn;
        }
    }

    protected void updateQuality() {
        if (item.sellIn == 0) {
            setQuality(item.quality - qualityDegradeRate * 2);
        } else {
            setQuality(item.quality - qualityDegradeRate);
        }
    }

    protected void updateSellIn() {
        setSellIn(item.sellIn - 1);
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
