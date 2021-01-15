package org.example

import org.apache.log4j.Logger
import java.text.DecimalFormat
import kotlin.jvm.JvmStatic

object App : ISales, ICustomer, ICashier, IMenu {
    var logger: Logger = Logger.getLogger(App::class.java)
    private const val STORE_RUNNING_TIME = 10000L
    private val managerList = listOf("Tom", "Chicol", "Eric")
    private var count = 0
    private val salesMap: MutableMap<String, Int> = hashMapOf()

    @JvmStatic
    fun main(args: Array<String>) {
        open()
        Thread.sleep(STORE_RUNNING_TIME)
        close()
    }

    private fun open() {
        logger.info("===오픈===")

        while (count < 5) {
            val customerName = customerList[count]
            val menuNum = (Math.random() * (menuList.size - 1)).toInt()
            val orderData = OrderData(customerName, menuNum)
            orderCoffee(orderData)

            deliverOrder(orderData)

            Thread.sleep(2000)
            addSales(salesMap, menuList[orderData.menuNum].name)

            count++
        }
    }

    private fun close() {
        logger.info("===영업종료===")
        logger.info("===정산===")
        calTotalSales(salesMap)
        printSales(salesMap)
    }

    private fun getPriceByName(name: String): Int {
        val list = menuList
        for (item in list) {
            if (item.name == name) return item.price
        }
        return 0
    }

    private fun setPriceFormat(price: Int): String {
        return DecimalFormat("###,###원").format(price)
    }


    override fun addSales(map: MutableMap<String, Int>, key: String) {
        val value = if (map.containsKey(key)) map[key] else 0
        if (value != null) {
            map[key] = value + 1
        }
    }

    override fun deliverOrder(orderData: OrderData) {
        val barista = Barista(managerList[count % 2], orderData, menuList)
        barista.start()
    }

    override val customerList: List<String>
        get() = listOf("Ada", "Beth", "Coco", "Daniel", "Emile")

    override fun orderCoffee(orderData: OrderData) {
        logger.info("[${managerList[2]}] ${orderData.customerName} 님이 ${menuList[orderData.menuNum].name} 를 주문하였습니다.")
    }

    override val menuList: List<MenuData>
        get() = listOf(
                MenuData("Americano", 3000),
                MenuData("Latte", 5000),
                MenuData("Tea", 4000),
                MenuData("Espresso", 4000),
                MenuData("Juice", 5000)
        )

    override fun calTotalSales(salesMap: MutableMap<String, Int>) {
        var totalSales = 0

        salesMap.forEach { (name, quantity) ->
            totalSales += quantity * getPriceByName(name)
        }

        logger.info("현재까지의 매출은 ${setPriceFormat(totalSales)} 입니다.")
    }

    override fun printSales(salesMap: MutableMap<String, Int>) {
        logger.info("판매 내역 조회: $salesMap")
    }

}