package org.example

internal class InternalPerson(val name: String, val age: Int) {
    fun getDescription() = "$name is $age years old"
    fun getDescription2() = "$name is $age years old"
}