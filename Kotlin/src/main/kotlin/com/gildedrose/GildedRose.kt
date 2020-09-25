package com.gildedrose

const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
const val CONJURED = "Conjured Mana Cake"

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
        AGED_BRIE -> if (sellIn <= 0) incrementQuality(increment = 2) else incrementQuality()
        BACKSTAGE_PASSES -> {
            when {
                sellIn <= 0 -> quality = 0
                sellIn < 6 -> incrementQuality(increment = 3)
                sellIn < 11 -> incrementQuality(increment = 2)
                else -> incrementQuality()
            }
        }
        CONJURED -> if (sellIn <= 0) decrementQuality(decrement = 4) else decrementQuality(decrement = 2)
        else -> if (sellIn <= 0) decrementQuality(decrement = 2) else decrementQuality()
    }

    sellIn -= 1
}

private fun Item.isConstant() = name == SULFURAS

private fun Item.incrementQuality(increment: Int = 1) {
    val result = quality + increment
    quality = if (result > 50)  50 else result
}

private fun Item.decrementQuality(decrement: Int = 1) {
    if (quality > 0) quality -= decrement
}
