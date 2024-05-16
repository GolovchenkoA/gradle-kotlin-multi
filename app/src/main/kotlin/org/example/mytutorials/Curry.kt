package org.example.mytutorials

//      https://en.wikipedia.org/wiki/Currying
//      https://realjenius.com/2017/08/24/kotlin-curry/
fun main(args: Array<String>) {

    // When variables are independent we can use Curring or Partial application:
    //    Curring is when we make a single parameter as constant.
    //    Partial application is when we left one variable and the rest of parameters become constants

    // Curring
    // Actually here is an example how we can rewrite one of function parameters and build a new function
    println("--------------Curring")
    val addOne = add(1)
    val val1 = addOne(2) // returns 3
    val val2 = addOne(3) // returns 4

    println(val1)
    println(val2)

    println("--------------")

    val quadrate = multi(2)
    val multi3 = multi(3)
    println("Quadrate 2: ${quadrate(2)}")
    println("Quadrate 4: ${quadrate(4)}")
    println("multi3 2: ${multi3(2)}")
    println("multi3 4: ${multi3(4)}")


    // Partial application. As I understood it's a subset of Curring
    println("--------------Partial application (!!!! this is still curring. See the description in the beginning")
    val adddOne = { b:Int -> addd(1, b) }
    val vall1 = adddOne(2) // returns 3

    println(vall1)

    println("Multi 2x3: ${multi(2)(3)}")

}

// returns a lambda
fun add(a:Int) : (Int) -> Int {
    return { b: Int -> a + b }
}
fun multi(a:Int) : (Int) -> Int {
    return { b: Int -> a * b }
}

// returns a number
fun addd(a:Int, b:Int):Int {
    return a + b;
}