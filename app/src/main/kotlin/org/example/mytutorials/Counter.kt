package org.example.mytutorials

fun main(args: Array<String>) {
    val c1:() -> Int = counter()
    c1()
    c1()
    c1()

    val c2 = counter()

    println("${c1()} ${c2()}")
}

fun counter(): () -> Int {
    var counter = 0
    return {++counter}
}
