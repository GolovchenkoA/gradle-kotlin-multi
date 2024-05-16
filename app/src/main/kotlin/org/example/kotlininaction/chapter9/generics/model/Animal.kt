package org.example.kotlininaction.chapter9.generics.model

open class Animal {
    fun feed() {
        println("Animal feed")
    }

    open fun voice() {
        println("Animal voice")
    }
}