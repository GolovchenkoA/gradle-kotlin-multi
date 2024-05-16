package org.example.fpkotlin.chapter2

fun increment(a : Int) : Int { return a + 1 }
fun incrementF(): (Int) -> Int = { it + 1 }
fun incrementF2(): (Int) -> Int = { a -> a + 1 }
fun incrementF3(): (a: Int) -> Int = { b -> b + 1 } // it works despite the variable names are different
fun multiplyF(): (Int) -> Int = { it * it }

fun <A> toString(a : A) : String {return a.toString()}
fun <A> toStringF() : (Any) -> String = {it.toString()}
fun <A> toStringF2() : (Any) -> String = {a -> a.toString()}




fun main(args: Array<String>) {
    // Int -> increment -> Int  Int -> toString -> String = Int -> String
    println(toString(increment(1)))

    println(incrementF().invoke(1))
    println(incrementF()(1))
    println(incrementF3()(1))

    //TODO: finish
}