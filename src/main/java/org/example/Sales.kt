package org.example

import org.example.App.logger
import java.text.DecimalFormat

class Sales {

    private val list = Object.menuList
    var salesMap: MutableMap<String, Int> = hashMapOf()

    fun calTotalSales() {
        var totalSales = 0

        salesMap.forEach { (name, quantity) ->
            totalSales += quantity * getPriceByName(name)
        }

        logger.info("현재까지의 매출은 ${setPriceFormat(totalSales)} 입니다.")
    }

    fun printSoldList() {
        logger.info("판매 내역 조회: $salesMap")
    }

    private fun getPriceByName(name: String) : Int{
        for (item in list){
            if(item.name==name) return item.price
        }
        return 0
    }

    private fun setPriceFormat(price: Int): String {
        return DecimalFormat("###,###원").format(price)
    }


}