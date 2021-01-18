package org.example

import org.example.App.logger
import org.example.App.menuList
import org.example.Constants.BARISTA_WORKING_TIME
import java.util.concurrent.BlockingQueue


/**
 * 바리스타 객체 - 음료 제조, 음료 전달
 */

class Barista(private val manager: String, private val queue: BlockingQueue<OrderData>) : Thread() {

    private lateinit var order: OrderData

    init {
        super.setName(manager)
    }

    override fun run() {
        try {
            while (queue.take().also { order = it }.customerName != null) {
                makeCoffee()
                sleep(BARISTA_WORKING_TIME)
                callCustomer()
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


    private fun makeCoffee(){
        logger.info("[${manager}] ${order.customerName}님의 ${menuList[order.menuNum].name} 만드는 중...")
    }

    private fun callCustomer(){
        logger.info("[${manager}] ${order.customerName}님의 ${menuList[order.menuNum].name}가 완성되었습니다.")
    }
}