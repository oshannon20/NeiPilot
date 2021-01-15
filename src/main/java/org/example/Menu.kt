package org.example


class Menu{
    val menuList: List<MenuData>
        get() = listOf(
                MenuData("Americano", 3000),
                MenuData("Latte", 5000),
                MenuData("Tea", 4000),
                MenuData("Espresso", 4000),
                MenuData("Juice", 5000)
        )
}