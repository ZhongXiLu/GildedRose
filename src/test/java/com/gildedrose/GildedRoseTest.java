package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    @DisplayName("At the end of each day our system lowers both values for every item")
    void testUpdateQuality() {
        int initialQuality = 100;
        Item item = new Item("Headhunter", 100, initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        int currentQuality = item.quality;
        int currentSellIn = item.sellIn;
        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isEqualTo(currentQuality - 1);
            assertThat(item.sellIn).isEqualTo(currentSellIn - 1);
            currentQuality = item.quality;
            currentSellIn = item.sellIn;
        }

    }

    @Test
    @DisplayName("Once the sell by date has passed, Quality degrades twice as fast")
    void testQuality_sellDateHasPassed() {
        Item item = new Item("Headhunter", 0, 100);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        int currentQuality = item.quality;
        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isEqualTo(currentQuality - 2);
            currentQuality = item.quality;
        }
    }

    @Test
    @DisplayName("The Quality of an item is never negative")
    void testQuality_neverNegative() {
        Item item = new Item("Headhunter", 1, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 20; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isNotNegative();
        }
    }

    @Test
    @DisplayName("'Aged Brie' actually increases in Quality the older it gets")
    void testAgedBrie() {
        Item item = new Item("Aged Brie", 1, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        int currentQuality = item.quality;
        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isGreaterThan(currentQuality);
            currentQuality = item.quality;
        }
    }

    @Test
    @DisplayName("The Quality of an item is never more than 50")
    void testQuality_maximum() {
        Item item = new Item("Aged Brie", 1, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 100; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isLessThanOrEqualTo(50);
        }
    }

    @Test
    @DisplayName("'Sulfuras', being a legendary item, never has to be sold or decreases in Quality")
    void testSulfuras() {
        int initialQuality = 10;
        Item item = new Item("Sulfuras, Hand of Ragnaros", 1, initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 100; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isEqualTo(initialQuality);
        }
    }

    @Test
    @DisplayName("'Backstage passes', like aged brie, increases in Quality as its SellIn value approaches" +
        "Quality increases by 2 when there are 10 days or less")
    void testBackStagePasses_lessThan10Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        int currentQuality = item.quality;
        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isEqualTo(currentQuality + 2);
            currentQuality = item.quality;
        }
    }

    @Test
    @DisplayName("'Backstage passes', like aged brie, increases in Quality as its SellIn value approaches" +
        "Quality increases by 3 when there are 5 days or less")
    void testBackStagePasses_lessThan5Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        int currentQuality = item.quality;
        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
            assertThat(item.quality).isEqualTo(currentQuality + 3);
            currentQuality = item.quality;
        }
    }

    @Test
    @DisplayName("'Backstage passes', like aged brie, increases in Quality as its SellIn value approaches" +
        "Quality drops to 0 after the concert")
    void testBackStagePasses_afterSellIn() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();
        assertThat(item.quality).isZero();
    }

}
