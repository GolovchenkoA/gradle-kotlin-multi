package org.example.kotlininaction.chapter8

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5)
    list.filter { it > 3 }.forEach { println(it) }

    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val sum2 = { x: Int, y: Int -> x + y }

    var canReturnNull: (Int, Int) -> Int? = { x, y -> if (x == y) null else x }

//    function can be absent
    var funOrNull: ((Int, Int) -> Int)? = null

    // name of function parameters can be changed

    fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
        /** do something **/
    }

    performRequest("http://url1") { code, content -> println(content) }
    performRequest("http://url1") { code, renamedContentVariable -> println(renamedContentVariable) }

    //    Higher-order function
    fun doAndPrintResult(f: (Int, Int) -> Int) {
        val result = f(1, 2)
        println("doAndPrintResult $result")
    }

    doAndPrintResult { x, y -> x + y + 1000 }
    doAndPrintResult { x, y -> y - x }


    // Example of using inner and external behavior
    fun doAndPrintResultString(f: (String) -> String) {
        val result = f("inner string 12345").replaceAfterLast("5", "updated string")
        println("doAndPrintResultString inner behavior:  $result")
    }

    //       This example doesn't accept a string as parameter and just return a string
    doAndPrintResultString {
        println("doAndPrintResultString Outer behavior 1")
        "outer string"
    }

    //       This example uses a string as parameter(from function), print it and return it back
    doAndPrintResultString { s ->
        println("doAndPrintResultString Outer behavior 2: $s")
        s
    }

// Substitute function
    println("Substitute function: " + "0123456".replaceAfterFun(3, "9"))



    // Filter
    fun String.filter(predicate: (Char) -> Boolean): String {
        val sb = StringBuilder()
        for (c in this) {
            if (predicate(c)) {
                sb.append(c)
            }
        }
        return sb.toString()
    }

    val filterResult = "abc123xyz".filter { it in 'a'..'z' }
    println("String filter result is: $filterResult")

//    High-order function with a default lambda value
    val joinToStringResult = listOf(1, 2, 3).joinToStringCustom(",")
    val joinToStringResult2 = listOf(1, 2, 3).joinToStringCustom(",") { s ->"$s-abc"}
    val joinToStringResult3 = listOf(1, 2, 3).joinToStringCustom(",") { "$it-abc"}

    println("joinToStringResult 1 (default transformer): $joinToStringResult")
    println("joinToStringResult 2: (custom transformer) $joinToStringResult2")
    println("joinToStringResult 3: (custom transformer) $joinToStringResult3")


    // Function with a nullable lambda
    optionalLambdaExample()
    optionalLambdaExample {
        println("optionalLambdaExample. Optional lambda output")
    }
}


// Top-level functions
fun printResult(f: (Int) -> Unit) {
    f(1)
    println("printResult inner behavior")
}


fun String.replaceAfterFun(index: Int, value: String): String {
    return this.substring(0, index) + value
}

fun <T> Collection<T>.joinToStringCustom(
    separator: String,
    prefix: String = "",
    postfix: String = "",
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

fun optionalLambdaExample(callback: (() -> Unit)? = null) {
    callback?.invoke()
    println("optionalLambdaExample inner behavior")
}