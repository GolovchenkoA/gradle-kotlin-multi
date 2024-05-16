package org.example.kotlininaction.chapter9.generics

import org.example.kotlininaction.chapter9.generics.model.Animal
import org.example.kotlininaction.chapter9.generics.model.Cat
import org.example.kotlininaction.chapter9.generics.model.HerdCovariant
import org.example.kotlininaction.chapter9.generics.model.HerdNonCovariant

fun main(args: Array<String>) {
    // Covariance vs Contravariant: See Figure 9.7.
    // For a covariant type Producer<T>, the subtyping is preserved, but for a contravariant type Consumer<T>,
    // the subtyping is reversed.

    // Covariance
    // https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science)
    // If A is a subtype of B, then List<A> is a subtype of List<B>.
    // When  Such classes or interfaces are called covariant.

//    For example, Producer<Cat> is a subtype of Producer<Animal> because Cat is a subtype of Animal.

//    Marking a type parameter of a class as covariant makes it possible to pass values of that class as function
    //    arguments and return values when the type arguments don’t exactly match the ones in the function definition.
    //    For example, imagine a function that takes care of feeding a group of animals, represented by the Herd class.
    //    The type parameter of the Herd class identifies the type of the animal in the herd.

    // !! Implementation of Herd class (see class Herd<T: Animal> ..) is not covariant
    println("We cannot feed cats because Herd<Cats> is not a subclass of Herd<Animals>")
    val herdNonCovariant = HerdNonCovariant<Cat>()
    herdNonCovariant.add(Cat())
    herdNonCovariant.add(Cat())

    takeCareOfCats(herdNonCovariant)

    println("We feed cats because HerdCovariant is covariant")
    val herdCovariant = HerdCovariant<Cat>()
    // We can read safely (class and subclasses but not write there)
//    herdCovariant.add(Cat())
//    herdCovariant.add(Cat())
    takeCareOfCatsCovariant(herdCovariant)

    // See Transform interface.
    // fun transform(value: T): T      means:
    // value: T - is in "in" position
    // return type T is in "out" position
    // see: Figure 9.6. The function parameter type is called in position, and the function return type is called out position.

}


//High-level functions

// Note that you can’t declare MutableList<T> as covariant on its type parameter, because it contains methods
// that take values of type T as parameters and return such values (therefore, T appears in both in and out positions).
// !!! I guess there is a mistake in the book and thy mean MutableList<out T> as covariant not MutableList<T> as covariant

//interface MyMutableList<out T> : List<T>, MutableCollection<T> {
//    override fun add(element: T): Boolean
//}

interface Transform<T> {
    fun transform(value: T): T
}

// Interface declared as covariant.
// We can read safely (class and subclasses but not write there)
// The out keyword on a type parameter of the class requires that all methods using T have T only in out positions
// and not in 'in' positions.
interface Producer<out T> {
    fun produce(): T
}

// Java example: Collection<String> is a subtype of Collection<? extends Object>
//interface Producer<? extends T> {
//    T produce()
//}


fun feedAll(animals: HerdNonCovariant<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}


fun feedAllCovariant(animals: HerdCovariant<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun takeCareOfCats(cats: HerdNonCovariant<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        println("feedAll(cats) cannot be called because Herd<T: Animal> is not covariant")
        //feedAll(cats) // Compilation error. Expected Herd<Animal> but got Herd<Cat>
    }
}

fun takeCareOfCatsCovariant(cats: HerdCovariant<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        println("we can call feedAll(cats) for the covariant class Herd ")
        feedAllCovariant(cats)
    }
}