package org.example

import org.apache.log4j.Logger
import kotlin.jvm.JvmStatic

object App : Cashier.ICashier {
    var logger: Logger = Logger.getLogger(App::class.java)
    private const val STORE_RUNNING_TIME = 10000L
    val managerList = listOf("Tom", "Chicol", "Eric")
    private var count = 0
    private val salesMap: MutableMap<String, Int> = hashMapOf()
    val menuList = Menu().menuList

    @JvmStatic
    fun main(args: Array<String>) {
        open()
        Thread.sleep(STORE_RUNNING_TIME)
        close()
    }

    private fun open() {
        logger.info("===오픈===")


        while (count < 5) {
            val customer = Customer()
            val customerName = customer.customerList[count]
            val menuNum = (Math.random() * (menuList.size - 1)).toInt()
            val orderData = OrderData(customerName, menuNum)
            customer.orderCoffee(orderData)

            deliverOrder(orderData)

            Thread.sleep(2000)
            Cashier().addSales(salesMap, menuList[orderData.menuNum].name)

            count++
        }
    }

    private fun close() {
        logger.info("===영업종료===")
        logger.info("===정산===")
        Sales().calTotalSales(salesMap)
        Sales().printSales(salesMap)
    }


    override fun deliverOrder(orderData: OrderData) {
        val barista = Barista(managerList[count % 2], orderData, menuList)
        barista.start()
    }


}