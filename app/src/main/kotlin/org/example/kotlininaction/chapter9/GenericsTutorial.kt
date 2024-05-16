package org.example.kotlininaction.chapter9

fun main(args: Array<String>) {
    // Disjoin collections
    val authors = listOf("Dmitry", "Svetlana")
    val readers = listOf("Dmitry", "Afansiy", "Petr", "Svetlana")

//    fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T>
    val filteredReaders = readers.filter { it !in authors }
    println(filteredReaders)


    val halfIntList = listOf(1, 2, 3, 4, 5).half()
    println(halfIntList)

//     listOf("1", "2").half() // is not allowed

    println("Max: ${max("1", "2")}")
//    max("1", 2)  // is not allowed




}

// Set upper bound for a generic type
interface NumericSum<T> {
    fun <T:Number> List<T>.sum() : T
    // Java example
//    <T extends Number> T sum(List<T> list).
}

fun <T:Number> List<T>.half() : List<T> {
    return this.subList(0, this.size - (this.size / 2))
}

fun <T: Comparable<T>> max(first: T, second: T) : T {
    return if (first > second) first else second
}

//Extending a generic class example
class StringList<String>: java.util.ArrayList<String>() {
 // ... implementation
}