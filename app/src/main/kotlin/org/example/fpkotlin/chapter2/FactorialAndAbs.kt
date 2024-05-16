package org.example.fpkotlin.chapter2

import org.example.fpkotlin.chapter2.Example.formatFactorial

object Example {

    fun abs(n: Int): Int =
        if (n < 0) -n
        else n

    fun factorial(i: Int): Int {
        fun go(n: Int, acc: Int): Int =
            if (n <= 0) acc
            else go(n - 1, n * acc)
        return go(i, 1)
    }

    fun formatAbs(x: Int): String {
        val msg = "The absolute value of %d is %d"
        return msg.format(x, abs(x))
    }

    fun formatFactorial(x: Int): String {
        val msg = "The factorial of %d is %d"
        return msg.format(x, factorial(x))
    }

    // Refactoring. it's a HOF
    fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
        val msg = "The %s of %d is %d."
        return msg.format(name, n, f(n))
    }
}

fun findFirst(ss: Array<String>, key: String): Int {
    tailrec fun loop(n: Int): Int =
        when {
            n >= ss.size -> -1
            ss[n] == key -> n
            else -> loop(n + 1)
        }
    return loop(0)
}

fun <T> findFirstGeneric(ss: Array<T>, f: (T) -> Boolean): Int {
    tailrec fun loop(n: Int): Int =
        when {
            n >= ss.size -> -1
            f(ss[n]) -> n
            else -> loop(n + 1)
        }
    return loop(0)
}



fun main() {
    println(Example.formatAbs(-42))
    println(formatFactorial(7))
    println(Example.formatResult("formatAbs", -42, Example::abs))
    println(Example.formatResult("formatFactorial", 7, Example::factorial))

    println("Pass function (anonymous) as parameter")
    Example.formatResult("absolute", -42, fun(n: Int): Int { return if (n < 0) -n else n })
    Example.formatResult("absolute", -42, { n -> if (n < 0) -n else n })

    println("call findFirst(..)")
    println(findFirst(arrayOf("a", "b", "c", "d"), "c"))

    println("call findFirstGeneric(..)")
    println(findFirstGeneric(arrayOf("a", "b", "c", "d")) { it == "c"})
}