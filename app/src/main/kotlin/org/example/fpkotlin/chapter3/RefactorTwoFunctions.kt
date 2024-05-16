package org.example.fpkotlin.chapter3

fun main(args: Array<String>) {
    val ints = List.of(10, 40, 50)
    println(sum(ints)) // 100
    println(sum2(ints)) // 100
    val doubles = List.of(10.0, 10.0, 2.0)
    println(product(doubles)) // 200
    println(product2(doubles)) // 200
}

fun sum(xs: List<Int>): Int = when (xs) {
    is Nil -> 0
    is Cons -> xs.head + sum(xs.tail)
}

fun product(xs: List<Double>): Double = when (xs) {
    is Nil -> 1.0
    is Cons -> xs.head * product(xs.tail)
}

// Refactored
// foldRight is not tail-recursive and will result in a StackOverflowError for large lists (we say it’s not stack-safe).
// Convince yourself that this is the case, and then write another general list-recursion function, foldLeft
fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> f(xs.head, foldRight(xs.tail, z, f))
    }



fun sum2(ints: List<Int>): Int =
    foldRight(ints, 0, { a, b -> a + b })

fun product2(dbs: List<Double>): Double =
    foldRight(dbs, 1.0, { a, b -> a * b })