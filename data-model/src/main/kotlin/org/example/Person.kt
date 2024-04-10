package org.example

class Person(val name: String, var age: Int) {

    init {
        age *= 2
    }

    val description: String
        get() = "$name is $age years old"

    var city: String = "Unknown"
        set(value) {
            if (value.isBlank()) {
                throw IllegalArgumentException("City name cannot be empty")
            }
            field = value
        }

    constructor(email: String) : this("Default name", 100) {
        //do something
    }
}