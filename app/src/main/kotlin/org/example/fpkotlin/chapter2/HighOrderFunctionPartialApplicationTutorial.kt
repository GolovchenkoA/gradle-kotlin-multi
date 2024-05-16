package org.example.fpkotlin.chapter2

// It's a template of a HOF (High order function) that takes a function with two arguments and partially applies it.
//fun <A, B, C> partial1(a: A, f: (A, B) -> C): (B) -> C = {
//    b -> f(a, b)
//}

 fun <A, B, C> partial1(a: A, f: (A, B) -> C): (B) -> C = {
    b -> f(a, b)
}

//since -> associates to the right, (A) -> ((B) -> C) can be written as (A) -> (B) -> C.
//fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = TODO()

//Exercise 2.3
//fun <A, B, C> curry(f: (A, B) -> C): (A) -> ((B) -> C) = { a: A -> { b: B -> f(a, b) } }
fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a: A -> { b: B -> f(a, b) } }
//Exercise 2.4
fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = { a: A, b: B -> f(a)(b) }

//Exercise 2.5
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { a: A -> f(g(a)) }

fun main(args: Array<String>) {

 val addToOne = partial1(1) { a: Int, b: Int -> a + b }
 val addToTen = partial1(10) { a: Int, b: Int -> a + b }
 println(addToOne(2))
 println(addToTen(2))


 val addCurry = curry { a: Int, b: Int -> a + b }
 println(addCurry(3)(3))

 val increment = curry { a: Int, b: Int -> a + b }(1)
 println("increment(5): ${increment(5)}") // 6

 val multiply = curry { a: Int, b: Int -> a * b }
 println("multiply(2)(3): ${multiply(2)(3)}") // 6

 val incr: (Int) -> Int = {x -> x + 1}

 val incrImpl = { x: Int -> x + 1 }
 val multiplyImpl = { x: Int -> x * x }

 println("incrImpl(5): ${incrImpl(5)}") // 6
 println("multiplyImpl(5): ${multiplyImpl(5)}") // 25

 val sum = {a: Int -> {b: Int -> a + b}}
 println("val sum = {a: Int -> {b: Int -> a + b}}")
 println(sum(2)(2))

}