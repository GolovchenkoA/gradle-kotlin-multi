package org.example.kotlininaction.chapter7

import java.time.LocalDate

class Car(val owner: String, val model: String): Comparable<Car> {

    val manufactureInfo: String by lazy {
        println("manufacture info lazy initialized")
        Thread.sleep(4000)
        "Car manufacture"
    }
    override fun compareTo(other: Car): Int {
        return compareValuesBy(this, other, Car::owner, Car::model)
    }
}

data class MutableCar(var owner: String, var model: String)

data class Point(val x: Int, val y: Int)
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Car.get(element: Int): String {
    return when(element) {
        0 -> owner
        1 -> model
        else -> throw IndexOutOfBoundsException()
    }
}

operator fun MutableCar.set(element: Int, value: String) {
    return when(element) {
        0 -> owner = value
        1 -> model = value
        else -> throw IndexOutOfBoundsException()
    }
}

operator fun Rectangle.contains(xy: Pair<Int, Int>): Boolean {
    // 0 until 10 is an open range. Means 10 is not included in this range
    return xy.first in upperLeft.x until lowerRight.x
            && xy.second in upperLeft.y until lowerRight.y
}

fun main(args: Array<String>) {
    println(Car("Artem", "A").compareTo(Car("Artem", "B")))
    println(Car("Artem", "A") < Car("Artem", "B"))

    // Square brackets implementation
    val car = Car("Artem", "Model X")
    println(car[0])
    println(car[1])


    // Square brackets implementation for mutation
    val mCar = MutableCar("Artem", "Model X")
    mCar[0] = "new owner"
    println(mCar)

    // Check if coordinates within a rectangle
    val rectangle = Rectangle(Point(0, 0), Point(10, 10))
    println("---- Check if coordinates within a rectangle")
    println(rectangle.contains(Pair(0, 9))) // true
    println(rectangle.contains(Pair(0, 10))) // false. because the implementation uses an open range (until)


    // Kotlin built-in function for ranges (..) - three dots operator
//    operator fun <T: Comparable<T>> T.rangeTo(that: T): ClosedRange<T>
//    !!!!!  The rangeTo function isn’t a member of LocalDate but rather is an extension function on Comparable,

    val now = LocalDate.now()
    val vacation = now..now.plusDays(2)
    println("vacation: $vacation")
    println(now.plusDays(1) in vacation)
    println(0..(9 + 1))
    (0..10).forEach { print(it) }
    println()


    //Skipped this implementation. Listing 7.13. Implementing a date range iterator


//    7.4. Destructuring declarations and component functions

    val (x, y) = Point(10, 20)
    println("Destructuring. X $x Y $y")

    // Listing 7.14. Using a destructuring declaration to return multiple values
    data class NamedComponents(val name: String, val extension: String)

    fun splitFilename(filename: String): NamedComponents {
        val (name, extension) = filename.split('.', limit = 2)
        return NamedComponents(name, extension)
    }

    val (name, ext) = splitFilename("file.kt")
    println("Name: $name, extension: $ext")


    // Listing 7.16. Using a destructuring declaration to iterate over a map
    println()
    println("Using a destructuring declaration to iterate over a map: ")
    fun printEntries(map: Map<Int, String>) {
        for ((key, value) in map) {
            println("$key: $value")
        }
    }

    printEntries(mapOf(1 to "one", 2 to "two", 3 to "three"))


//    Lazy initialization
    val manufactureInfo = car.manufactureInfo
    println("manufactureInfo call 1: $manufactureInfo")
    println("manufactureInfo call 2: $manufactureInfo")
}