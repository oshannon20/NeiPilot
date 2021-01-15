package org.example

import org.example.App.logger


/**
 * 바리스타 객체 - 음료 제조, 음료 전달
 */

class Barista(private val manager: String, private val order: OrderData, private val menuList: List<MenuData>) : Thread() {

    override fun run() {
        try {
            makeCoffee()
            sleep(3000)
            callCustomer()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun makeCoffee(){
        logger.info("[$manager] ${order.customerName} 님의 ${menuList[order.menuNum].name} 만드는 중...")
    }

    private fun callCustomer(){
        logger.info("[$manager] ${order.customerName} 님의 ${menuList[order.menuNum].name} 가 완성되었습니다.")
    }
}