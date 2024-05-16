package org.example.kotlininaction.chapter9.generics.model

class HerdNonCovariant<T: Animal> {
    private val animals = mutableListOf<T>()
    val size: Int
        get() = animals.size

    operator fun get(i: Int): T {
        return animals[i]
    }

    fun add(animal: T) {
        animals += animal
    }
}