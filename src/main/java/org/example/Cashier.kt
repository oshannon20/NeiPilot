package org.example

import org.example.App.logger
import org.example.App.menuList
import org.example.Constants.CASHIER_WORKING_TIME
import org.example.Constants.CUSTOMER_SIZE
import java.util.concurrent.BlockingQueue

/**
 * 캐셔 객체 - POS기에 주문 전달, 바리스타에 주문 전달
 */

class Cashier(private val manager: String, private val queue: BlockingQueue<OrderData>) : Thread() {
    lateinit var orderData: OrderData

    init {
        super.setName(manager)
    }

    override fun run() {
        for (i in 0 until CUSTOMER_SIZE) {
            makeRandomOrder(i)
            try {
                if(i!=0) sleep(CASHIER_WORKING_TIME)
                logger.info("[$manager] ${orderData.customerName}님이 ${menuList[orderData.menuNum].name} 주문 하셨습니다.")
                queue.put(orderData)
                addSales(App.salesMap, menuList[orderData.menuNum].name)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        val exit = OrderData(null, 0)
        try {
            queue.put(exit)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun makeRandomOrder(i: Int) {
        val menuNum = (Math.random() * (menuList.size - 1)).toInt()
        orderData = OrderData("손님${i+1}", menuNum)
        Customer(orderData)
    }

    private fun addSales(map: MutableMap<String, Int>, key: String) {
        val value = if (map.containsKey(key)) map[key] else 0
        if (value != null) {
            map[key] = value + 1
        }
    }
}