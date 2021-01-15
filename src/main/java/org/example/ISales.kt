package org.example


/**
 * POS(Point of Sales) 객체 - 총 매출 계산, 판매 내역 조회
 */
interface ISales {
    fun calTotalSales(salesMap: MutableMap<String, Int>)
    fun printSales(salesMap: MutableMap<String, Int>)
}
