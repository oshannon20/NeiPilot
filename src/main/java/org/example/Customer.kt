package org.example

import org.example.App.logger
import org.example.App.menuList
import org.example.Constants.CUSTOMER_SIZE

/**
 * 손님 객체 - 메뉴 주문
 */

class Customer(private val orderData: OrderData) {
    init {
        orderCoffee()
    }

    private fun orderCoffee() {
        logger.info("[${orderData.customerName}] ${menuList[orderData.menuNum].name} 주문할게요.")
    }

}