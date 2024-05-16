package org.example.fpkotlin.chapter3

fun main(args: Array<String>) {

    // It works because Nothing is a subtype of all types, which means in conjunction with the variance annotation
    val ex1: List<Double> = Nil
    val ex2: List<Int> = Cons(1, Nil)
    val ex3: List<String> = Cons("a", Cons("b", Nil))

    val ints = arrayOf(11, 22, 33, 44, 55)
    val sliceArray = ints.sliceArray(1 until ints.size)
    ints.forEach { i -> println("$i") }
    println("-----sliceArray")
    sliceArray.forEach { i -> println("$i") }
}

sealed class List<out A> {
    companion object {

        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> empty(): List<A> = Nil

    }
}

// Nothing is a subtype of all types
object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()