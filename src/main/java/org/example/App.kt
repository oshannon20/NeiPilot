package org.example

import org.apache.log4j.Logger
import org.example.Constants.CUSTOMER_SIZE
import org.example.Constants.STORE_RUNNING_TIME
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import kotlin.jvm.JvmStatic

object App {
    var logger: Logger = Logger.getLogger(App::class.java)
    val salesMap: MutableMap<String, Int> = hashMapOf()
    val menuList = Menu().menuList

    @JvmStatic
    fun main(args: Array<String>) {
        open()
        Thread.sleep(STORE_RUNNING_TIME)
        close()
    }

    private fun open() {
        val queue: BlockingQueue<OrderData> = ArrayBlockingQueue(CUSTOMER_SIZE)
        Manager().mangerList.forEach { managerData ->
            when (managerData.type) {
                "Cashier" -> {
                    val cashier = Cashier(managerData.name, queue)
                    cashier.start()
                }
                "Barista" -> {
                    val barista = Barista(managerData.name, queue)
                    barista.start()
                }
            }
        }
        logger.info("===오픈===")
    }

    private fun close() {
        logger.info("===영업종료===")
        logger.info("===정산===")
        Sales(salesMap)
    }
}
