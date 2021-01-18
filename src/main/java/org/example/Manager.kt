package org.example

class Manager {
    val mangerList : List<ManagerData>
    get() = listOf(
        ManagerData("Eric", "Cashier"),
        ManagerData("Chicol", "Barista"),
        ManagerData("Tom", "Barista")
    )
}