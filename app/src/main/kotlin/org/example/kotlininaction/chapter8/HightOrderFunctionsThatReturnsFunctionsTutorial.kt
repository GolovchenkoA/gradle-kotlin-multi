package org.example.kotlininaction.chapter8

fun main(args: Array<String>) {
    // 8.1.5. Returning functions from functions
    val standardClientDelivery = getShippingCost(CLIENT_TYPE.STANDARD)
    val standardClientDeliveryCost = standardClientDelivery(Order(10))
    val goldClientDelivery = getShippingCost(CLIENT_TYPE.GOLD)
    val goldClientDeliveryCost = goldClientDelivery(Order(10))

    println("standardClientDeliveryCost: $standardClientDeliveryCost")
    println("goldClientDeliveryCost: $goldClientDeliveryCost")


    // Contacts filter
    val contacts = listOf(Contact("Dima", "Bima", "555-555-5"),
        Contact("Sasha", "Kasha", "777-777-77"),
        Contact("Misha", "Kisha"))

    println("Contacts 1:")
    val contactListFilter = ContactListFilter();
    contacts.filter(contactListFilter.getPredicate()).forEach { println(it) }
    println("Contacts 2:")
    contactListFilter.prefixString = "K"
    contacts.filter(contactListFilter.getPredicate()).forEach { println(it) }

}

class Order(val items: Int)

enum class CLIENT_TYPE {
    STANDARD, SILVER, GOLD
}

fun getShippingCost(client : CLIENT_TYPE) : (Order) -> Double {
    val price = 10.0
    when (client) {
        CLIENT_TYPE.STANDARD -> return { order -> price * order.items }
        CLIENT_TYPE.SILVER -> return { order -> 0.9 * price * order.items }
        CLIENT_TYPE.GOLD -> return { order -> 0.8 * price * order.items }
    }
}