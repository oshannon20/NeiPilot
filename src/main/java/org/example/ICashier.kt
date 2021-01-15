package org.example

/**
 * 캐셔 객체 - POS기에 주문 전달, 바리스타에 주문 전달
 */
interface ICashier {
    fun addSales(map: MutableMap<String, Int>, key: String)
    fun deliverOrder(orderData: OrderData)
}