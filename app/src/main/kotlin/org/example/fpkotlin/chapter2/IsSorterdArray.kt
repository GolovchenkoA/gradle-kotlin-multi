package org.example.fpkotlin.chapter2

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    tailrec fun go(head: A, tail: List<A>, order: (A, A) -> Boolean): Boolean =
        when {
            tail.size == 1 -> true
            order(head, tail.first()) -> go(tail.head, tail.tail, order)
            else -> false
        }
    return go(aa.head, aa.tail, order)
}

fun main(args: Array<String>) {
    val sortedIntList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val sortedStringList = listOf("a", "b", "c")
    val intResult = isSorted(sortedIntList, fun(a: Int, b: Int): Boolean {return a < b})
    val stringResult = isSorted(sortedStringList, fun(a: String, b: String): Boolean {return a < b})
    val stringResult2 = isSorted(sortedStringList) {a, b -> a < b}

    println("Integer list is sorted $intResult")
    println("String list is sorted $stringResult")
    println("String list is sorted $stringResult2")
}