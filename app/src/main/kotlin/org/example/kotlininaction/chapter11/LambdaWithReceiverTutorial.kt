package org.example.kotlininaction.chapter11

fun main(args: Array<String>) {
    val simpleLambda = {number: Int -> "String with number $number"}
    println(simpleLambda(42))
//    val lambdaWithReceiver:Int.() -> String = {number: Int -> "String with number $number"}
//    println(simpleLambda(42))

    //Example 1: lambda with receiver
    printNumber {
        // here is the lambda
        "String with number 1 $this"
    }

    printNumber2 {
        "String with number 2 $this"
    }

    //Example 2: lambda with receiver
    val printNum: Int.() -> Unit = { println("printNum: $this") }
    (42).printNum()

    //Example 3: lambda with receiver
    val appendExclamation: StringBuilder.() -> Unit = {this.append("!")}
    val sb = StringBuilder("lambda with receiver. Example 3")
    sb.appendExclamation()
    println(sb)

    // Built-in functions
//    If you don’t care about the result, these functions are interchangeable:
//    Basically, all apply and with do is invoke the argument of an extension function type on the provided receiver.
//    The apply function is declared as an extension to that receiver, whereas with takes it as a first argument.
//    Also, apply returns the receiver itself, but with returns the result of calling the lambda.
    val map = mutableMapOf(1 to "one")
    map.apply { this[2] = "two"}
    val r = with (map) {
        this[3] = "three"
//        "with (map) result" // Uncomment this line if you want to return something instead of kotlin.Unit
    }
    println(map) //{1=one, 2=two, 3=three}
    println(r)
}

fun printNumber(f: Int.() -> String) { println(f(42)) }
fun printNumber2(f: Int.() -> String) = println((42).f())