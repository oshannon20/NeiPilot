package org.example

/**
 * 손님 객체 - 메뉴 주문
 */
interface ICustomer {
    val customerList: List<String>
    fun orderCoffee(orderData: OrderData)
}