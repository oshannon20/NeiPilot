package org.example

import org.example.App.logger
import org.example.App.managerList
import org.example.App.menuList

/**
 * 손님 객체 - 메뉴 주문
 */

class Customer{
    val customerList: List<String>
        get() = listOf("Ada", "Beth", "Coco", "Daniel", "Emile")

    fun orderCoffee(orderData: OrderData){
        logger.info("[${managerList[2]}] ${orderData.customerName} 님이 ${menuList[orderData.menuNum].name} 를 주문하였습니다.")
    }
}