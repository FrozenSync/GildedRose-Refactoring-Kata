package com.gildedrose

const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.updateQuality()
        }
    }
}

private fun Item.updateQuality() {
    if (isConstant()) return

    when (name) {
        AGED_BRIE -> incrementQuality()
        BACKSTAGE_PASSES -> {
            incrementQuality()

            if (sellIn < 11) {
                incrementQuality()
            }
            if (sellIn < 6) {
                incrementQuality()
            }
        }
        else -> decrementQuality()
    }

    sellIn = sellIn - 1

    if (sellIn < 0) {
        when (name) {
            AGED_BRIE -> incrementQuality()
            BACKSTAGE_PASSES -> quality = 0
            else -> decrementQuality()
        }
    }
}

private fun Item.isConstant() = name == SULFURAS

private fun Item.incrementQuality(increment: Int = 1) {
    if (quality < 50) quality += increment
}

private fun Item.decrementQuality(decrement: Int = 1) {
    if (quality > 0) quality -= decrement
}
