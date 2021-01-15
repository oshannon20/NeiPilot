package org.example

/**
 * 캐셔 객체 - POS기에 주문 전달, 바리스타에 주문 전달
 */

class Cashier {
    interface ICashier {
        fun deliverOrder(orderData: OrderData)
    }

    fun addSales(map: MutableMap<String, Int>, key: String) {
        val value = if (map.containsKey(key)) map[key] else 0
        if (value != null) {
            map[key] = value + 1
        }
    }
}