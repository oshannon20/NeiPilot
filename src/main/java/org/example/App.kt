package org.example

import org.apache.log4j.Logger
import kotlin.jvm.JvmStatic

object App {
    var logger: Logger = Logger.getLogger(App::class.java)
    private val sales = Sales()
    private val managerList = listOf("Tom", "Chicol", "Eric")

    @JvmStatic
    fun main(args: Array<String>) {
        open()
        Thread.sleep(10000)
        close()
        calculateTotalSales()
    }

    private fun open() {
        logger.info("===오픈===")
        var count = 0
        while (count < 5) {
            val customerName = Object.customerList[count]
            val menuNum = (Math.random() * (Object.menuList.size - 1)).toInt()
            val order = Order(customerName, menuNum)

            logger.info("[${managerList[2]}] ${order.customerName} 님이 ${Object.menuList[order.menuNum].name} 를 주문하였습니다.")

            val makeThread = MakeThread(managerList[count%2], order)
            makeThread.start()

            Thread.sleep(2000)
            addSales(sales.salesMap, Object.menuList[order.menuNum].name)

            count++
        }
    }

    private fun close() {
        logger.info("===종료===")
    }

    private fun calculateTotalSales() {
        logger.info("====정산====")
        sales.printSoldList()
        sales.calTotalSales()
    }


    private fun addSales(map: MutableMap<String, Int>, key: String) {
        val value = if (map.containsKey(key)) map[key] else 0
        if (value != null) {
            map[key] = value + 1
        }
    }
}