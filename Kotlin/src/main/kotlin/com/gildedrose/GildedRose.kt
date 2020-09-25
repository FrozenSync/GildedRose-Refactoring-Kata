package com.gildedrose

const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == SULFURAS) {
                continue
            }

            when (item.name) {
                AGED_BRIE -> if (item.quality < 50) item.quality += 1
                BACKSTAGE_PASSES -> {
                    if (item.quality < 50) item.quality += 1

                    if (item.sellIn < 11) {
                        if (item.quality < 50) item.quality += 1
                    }
                    if (item.sellIn < 6) {
                        if (item.quality < 50) item.quality += 1
                    }
                }
                else -> if (item.quality > 0) item.quality -= 1
            }

            item.sellIn = item.sellIn - 1

            if (item.sellIn < 0) {
                when (item.name) {
                    AGED_BRIE -> if (item.quality < 50) item.quality += 1
                    BACKSTAGE_PASSES -> item.quality = 0
                    else -> if (item.quality > 0) item.quality -= 1
                }
            }
        }
    }
}
