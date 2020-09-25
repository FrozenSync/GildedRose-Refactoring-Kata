package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Name of an item remains the same after an update`() {
        val items = arrayOf(Item("foo", 0, 0))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals("foo", result.name)
    }

    @Test
    fun `Quality of an item degrades each passing day`() {
        val items = arrayOf(Item("foo", 69, 420))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(68, result.sellIn)
        assertEquals(419, result.quality)
    }

    @Test
    fun `Quality of an item degrades twice as fast once the sell by date has passed`() {
        val items = arrayOf(Item("foo", 0, 420))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(-1, result.sellIn)
        assertEquals(418, result.quality)
    }

    @Test
    fun `Quality of an item is never negative`() {
        val items = arrayOf(Item("foo", 0, 0))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(-1, result.sellIn)
        assertEquals(0, result.quality)
    }

    @Test
    fun `Quality of "Aged Brie" increases the older it gets`() {
        val items = arrayOf(Item("Aged Brie", 6, 6))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(5, result.sellIn)
        assertEquals(7, result.quality)
    }

    @Test
    fun `Quality of "Aged Brie" increases twice as fast past sellIn date`() {
        val items = arrayOf(Item("Aged Brie", 0, 6))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(-1, result.sellIn)
        assertEquals(8, result.quality)
    }

    @Test
    fun `Quality of "Aged Brie" never decreases`() {
        val items = arrayOf(Item("Aged Brie", -6, 50))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(-7, result.sellIn)
        assertEquals(50, result.quality)
    }

    @Test
    fun `Quality of "Backstage passes" increases the older it gets`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 66, 0))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(65, result.sellIn)
        assertEquals(1, result.quality)
    }

    @Test
    fun `Quality of "Backstage passes" increases by 2 when there are 10 days or less`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 33))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(9, result.sellIn)
        assertEquals(35, result.quality)
    }

    @Test
    fun `Quality of "Backstage passes" increases by 3 when there are 5 days or less`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 33))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(4, result.sellIn)
        assertEquals(36, result.quality)
    }

    @Test
    fun `Quality of "Backstage passes" drops to 0 after the concert`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 50))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(-1, result.sellIn)
        assertEquals(0, result.quality)
    }

    @Test
    fun `Quality of an item is never increases beyond 50`() {
        val items = arrayOf(
                Item("Aged Brie", 66, 50),
                Item("Backstage passes to a TAFKAL80ETC concert", 66, 50)
        )
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items

        assertEquals(65, result[0].sellIn)
        assertEquals(50, result[0].quality)

        assertEquals(65, result[1].sellIn)
        assertEquals(50, result[1].quality)
    }

    @Test
    fun `Quality of "Sulfuras" is always 80 and never has to be sold`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 666, 80))
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items[0]

        assertEquals(666, result.sellIn)
        assertEquals(80, result.quality)
    }

    @Test
    fun `Quality of "Conjured" items degrade twice as fast as normal items`() {
        val items = arrayOf(
                Item("Conjured Mana Cake", 6, 80),
                Item("Conjured Mana Cake", 0, 80)
        )
        val subject = GildedRose(items)

        subject.updateQuality()
        val result = subject.items

        assertEquals(5, result[0].sellIn)
        assertEquals(78, result[0].quality)

        assertEquals(-1, result[1].sellIn)
        assertEquals(76, result[1].quality)
    }
}
