package org.example.kotlininaction.chapter9.generics.model

// !!!! The out keyword on a type parameter of the class requires that all methods using T have T only in out positions
// and not in 'in' positions.

class HerdCovariant<out T: Animal> {
    private val animals = mutableListOf<T>()
    val size: Int
        get() = animals.size

    // Any code calling get on a Herd<Animal> will work perfectly if the method returns a Cat,
    // because Cat is a subtype of Animal.
    operator fun get(i: Int): T { // Here :T means ": out T" that's why compiler doesn't comply
        return animals[i]
    }

    //  To guarantee type safety, it can be used only in so-called out positions,
    //  meaning the class can produce values of type T but not consume them.
//    fun add(animal: T) {
//        animals += animal
//    }
}