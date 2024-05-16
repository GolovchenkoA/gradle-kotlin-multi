package org.example

fun main() {
    println(findSquare() (2))
    println(findSquare2(3))
    doSomething(10, ::println)
    doSomething(10) { println("Here is a number $it")}

    println("Extension function square " + (4).square())
    println("Extension function multiply " + (4).multiply(4))

    val localSum: Int.(Int) -> Int = { other -> plus(other) }
    val sumFunAnonymous = fun Int.(other: Int): Int = this + other

    println("Extension function LocalSum: ${localSum.invoke(1,2)}")
    val apply = localSum.apply { 2 + 2 }
    println("Extension function LocalSum2: $apply")
    println("Extension function Anonymous Function Sum : ${(1).sumFunAnonymous(2)}")
    println("Extension function Sum : ${sum().invoke(1,2)}")



    // Example lambda with receiver
    println("\n---------------------------")
    println("Example lambda with receiver:")
    println("---------------------------\n")


    val htmlObject0 = htmlFun0 {       // lambda with receiver begins here
//        body()   // calling a method on the receiver object
//        body2()   // calling a method on the receiver object 2
//        println(returnNumber())
//
////        println("htmlFun println it: $it")  // is not allowed
//        println("htmlFun println this: $this")
        returnNumber()
    }

    println("htmlObject0 $htmlObject0")

    val htmlObject1 = htmlFun {       // lambda with receiver begins here
        body()   // calling a method on the receiver object
        body2()   // calling a method on the receiver object 2
        println(returnNumber())

//        println("htmlFun println it: $it")  // is not allowed
        println("htmlFun println this: $this")
    }

    println(htmlObject1)

    htmlFun2 {
        println("htmlFun2 println it: $it")
        println("htmlFun2 println it number: ${it.returnNumber()}")
//        println("htmlFun2 println this: $this") // is not allowed
    }

    myHtmlFunNotImplemented {

    }
}

private val myLambdaMultiply = {x: Int -> x * x}
private val myLambdaSum: (Int, Int) -> Int = { x: Int, y: Int -> x + y}

fun findSquare() : (Int) -> Int = {it * it}
fun findSquare2(i: Int): Int {return i * i}
fun Int.square(): Int { return this * this }
fun Int.multiply(i: Int): Int { return this * i }

fun sum(): Int.(Int) -> Int = { other -> plus(other) }


fun <T> doSomething(i: T, f: (T) -> Unit) {
    f(i)
}

// HTML
class HTML (val description: String) {
    fun body() { println("method body has called") }
    fun body2() { println("method body2 has called") }
    fun returnNumber() : Int { return 42 }

    override fun toString(): String {
        return description
    }
}

fun htmlFun0(htmlMethodCall: HTML.() -> Int): HTML {
    val html = HTML("My HTML class 0")  // create the receiver object
    html.htmlMethodCall()        // pass the receiver object to the lambda
    return html
}

fun htmlFun(htmlMethodCall: HTML.() -> Unit): HTML {
    val html = HTML("My HTML class 1")  // create the receiver object
    html.htmlMethodCall()        // pass the receiver object to the lambda
    return html
}

fun htmlFun2(htmlLambda: (HTML) -> Unit): HTML {
    val html = HTML("My HTML class 2")
    htmlLambda.invoke(html)
    return html
}

//https://kotlinlang.org/docs/lambdas.html#function-types
//https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver
fun myHtmlFunNotImplemented(htmlMethodCall: HTML.() -> Unit): HTML.() -> Unit = htmlMethodCall