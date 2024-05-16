package org.example.kotlininaction.chapter9.generics.model

open class Cat : Animal() {
    fun cleanLitter() {
        println("Clean cat's litter")
    }

    override fun voice() {
        println("Cat voice")
    }
}