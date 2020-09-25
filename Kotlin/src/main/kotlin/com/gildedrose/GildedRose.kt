package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") {
                continue
            }

            if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality > 0) {
                    item.quality = item.quality - 1
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }
                    }
                }
            }

            item.sellIn = item.sellIn - 1

            if (item.sellIn < 0) {
                when (item.name) {
                    "Aged Brie" -> if (item.quality < 50) item.quality += 1
                    "Backstage passes to a TAFKAL80ETC concert" -> item.quality = 0
                    else -> if (item.quality > 0) item.quality -= 1
                }
            }
        }
    }
}
