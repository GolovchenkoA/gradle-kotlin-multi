package org.example.kotlininaction.chapter9.generics

import org.example.kotlininaction.chapter9.generics.model.Animal
import org.example.kotlininaction.chapter9.generics.model.Cat
import org.example.kotlininaction.chapter9.generics.model.MaineCoon

fun main(args: Array<String>) {

    // Note
    // Use-site variance declarations in Kotlin correspond directly to Java bounded wildcards. MutableList<out T> in Kotlin
    // means the same as MutableList<? extends T> in Java. The in-projected MutableList<in T> corresponds to Java’s
    // MutableList<? super T>.


    // Read-only methods
    println("call fun <T: Animal>animalVoice(animals: List<T>)")
    animalVoice(listOf(Animal()))
    animalVoice(listOf(Cat()))
    animalVoice(listOf(MaineCoon()))

    println("call fun <T: Animal>animalVoiceOut(animals: List<out T>)")
    animalVoiceOut(listOf(Animal()))
    animalVoiceOut(listOf(Cat()))
    animalVoiceOut(listOf(MaineCoon()))

    println("call fun <T: Cat>catVoice(animals: List<T>)")
//    catVoice(listOf(Animal())) // is not allowed because Animal is not a subtype of Cat
    catVoice(listOf(Cat()))
    catVoice(listOf(MaineCoon()))

    println("call fun <T: Cat>catVoice(animals: List<out T>)")
//    catVoiceOut(listOf(Animal())) // is not allowed because Animal is not a subtype of Cat
    catVoiceOut(listOf(Cat()))
    catVoiceOut(listOf(MaineCoon()))


    // Write methods

//    println("call fun <T: Animal>animalVoiceWithUpdate(animals: MutableCollection<T>, animal: T)")
//    addAnimal(mutableListOf(Animal()), Animal())
//    addAnimal(mutableListOf(Cat()), Cat())
//    addAnimal(mutableListOf(MaineCoon()), MaineCoon())
//
//    addAnimal(mutableListOf(Animal()), Cat())


    val numbers = mutableListOf(1,2,3)
    val objects = mutableListOf<Any>()
    copyData(numbers, objects)
    println("Copied numbers to objects: $objects")

    val cats = mutableListOf(Cat(), Cat())
    val catsMaineCoon = mutableListOf(MaineCoon(), MaineCoon())
    val animals = mutableListOf<Animal>()
    copyData(cats, animals)
    copyData(catsMaineCoon, animals)
    println("Copied cats(+ MaineCoon) to animals list: $animals")

    copyData(catsMaineCoon, cats) // it works because MineCoon is a subtype of Cat


//    copyData(cats, catsMaineCoon) // it doesn't works because Cat is not a subtype of MineCoon
//    copyData(animals, catsMaineCoon) // it doesn't works because Animal is not a subtype of MineCoon
}


fun <T> copyData(source: List<out T>, dest: MutableList<in T>) {
    source.forEach {i -> dest.add(i)}
}
// The same behavior
//fun <T: R, R> copyData(source: List<T>, dest: MutableList<R>) {
//    source.forEach {i -> dest.add(i)}
//}


fun <T: Animal>animalVoice(animals: List<T>) {
    animals.forEach{a -> a.voice()}
}

fun <T: Animal>animalVoiceOut(animals: List<out T>) {
    animals.forEach{a -> a.voice()}
}

fun <T: Cat>catVoice(animals: List<T>) {
    animals.forEach{a -> a.voice()}
}

fun <T: Cat>catVoiceOut(animals: List<out T>) {
    animals.forEach{a -> a.voice()}
}


//fun <T: Animal>addAnimal(animals: MutableCollection<in T>, animal: T): MutableCollection<out T> {
//    animals.add(animal)
//    return animals
//}


// A class or interface can be covariant on one type parameter and contravariant on another.
// The classic example is the Function interface. The following declaration shows a one-parameter Function:
//
interface Function1<in P, out R> {
    operator fun invoke(p: P): R
}

//fun <T:Cat, R> inAndOut(input: T): R {
//    return
//}