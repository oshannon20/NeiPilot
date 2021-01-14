package org.example

import org.example.App.logger
import org.example.Object.menuList


class MakeThread(private val manager: String, private val order: Order) : Thread() {

    override fun run() {
        try {
            logger.info("[$manager] ${order.customerName} 님의 ${menuList[order.menuNum].name} 만드는 중...")
            sleep(3000)

            // 음료 제작 완료
            logger.info("[$manager] ${order.customerName} 님의 ${menuList[order.menuNum].name} 가 완성되었습니다.")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}