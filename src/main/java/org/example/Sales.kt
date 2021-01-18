package org.example

import java.text.DecimalFormat


/**
 * POS(Point of Sales) 객체 - 총 매출 계산, 판매 내역 조회
 */
class Sales (private val salesMap: MutableMap<String, Int>){
    init {
        calTotalSales()
        printSales()
    }

    private fun calTotalSales() {
        var totalSales = 0

        salesMap.forEach { (name, quantity) ->
            totalSales += quantity * getPriceByName(name)
        }

        App.logger.info("현재까지의 매출은 ${setPriceFormat(totalSales)} 입니다.")
    }

    private fun printSales() {
        App.logger.info("판매 내역 조회: $salesMap")
    }


    private fun getPriceByName(name: String): Int {
        val list = App.menuList
        for (item in list) {
            if (item.name == name) return item.price
        }
        return 0
    }

    private fun setPriceFormat(price: Int): String {
        return DecimalFormat("###,###원").format(price)
    }
}