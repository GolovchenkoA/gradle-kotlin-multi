package org.example

import kotlin.reflect.KProperty

/**
 * Source: https://github.com/hhariri/oreilly-kotlin-course/blob/master/advanced/src/main/kotlin/com/hadihariri/kotlincourse/delegation/DelegatingProperties.kt
 */

class Service {
    var someProperty: String by ExternalFunctionality()
}

class ExternalFunctionality  {

    var backingField = "Default"
    operator fun getValue(service: Service, property: KProperty<*>): String {
        println("\n\ngetValue called with params: \n" +
                "service: $service\n" +
                "property: ${property.name}"
        )
        println("------------------------------------\n\n")
        return backingField
    }

    operator fun setValue(service: Service, property: KProperty<*>, value: String) {
        backingField = value
    }

}

fun main(args: Array<String>) {

    val service = Service()

    println(service.someProperty)
    service.someProperty = "New Value"
    println(service.someProperty)

}