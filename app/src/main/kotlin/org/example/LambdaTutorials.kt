package org.example

class LambdaTutorials {

}

fun main(args: Array<String>) {
//    Lambda syntax
//    1. people.maxBy({p:Person -> p.age}) or people.maxBy {p:Person -> p.age }
//    1.1. people.maxBy {p -> p.age}
//    2. people.maxBy({Person::age})
//    3. people.maxBy( {it.age} )

//    5. { println(42) }()
//    6. run { println(42) }


//------------Listing 5.6. Passing a lambda as a named argument
//>>> val people = listOf(Person("Alice", 29), Person("Bob", 31))
//>>> val names = people.joinToString(separator = " ",  transform = { p: Person -> p.name })
//>>> println(names)
//Alice Bob

//  Listing 5.7. Passing a lambda outside of parentheses
//people.joinToString(" ") { p: Person -> p.name }


//    ------------ lambda as a variable
// val getAge = { p: Person -> p.age }
// people.maxBy(getAge)

// -- Lambda with multiple rows
// val sum = { x: Int, y: Int ->
//    println("Computing the sum of $x and $y...")
//    x + y
// }
//>>> println(sum(1, 2))
//Computing the sum of 1 and 2...
//3

// ----Another example how use lambdas
    var counter = 0
    val inc = { counter++ }

    val plusOne: (Int) -> Int = { it + 1 }
    plusOne(1)
    
//    ---  the following code isn’t a correct way to count button clicks:
//    See details in 'Kotlin in action'. Paragraph: Capturing a mutable variable: implementation details

//    This function will always return 0. Even though the onClick handler will modify the value of clicks,
//    you won’t be able to observe the modification, because the onClick handler will be called after
//    the function returns. A correct implementation of the function would need to store the click count not in
//    a local variable, but in a location that remains accessible outside of the function—for example, in a property
//    of a class.

    fun tryToCountButtonClicks(button: Button): Int {
        var clicks = 0
        button.onClick { clicks++ }
//        button.onClick { println("Button has clicked") }
        return clicks
    }

    val button = Button()
    tryToCountButtonClicks(button)
    tryToCountButtonClicks(button)
    var buttonClicks = tryToCountButtonClicks(button)
    buttonClicks = tryToCountButtonClicks(button)
    println("Returns only last click because the function stores this counter: $buttonClicks")

//    --- Store clicks counter outside the function
    fun tryToCountButtonClicks2(button: Button, lambdaFunction: () -> Unit) {
        button.onClick { println("Button 2 clicked") }
        button.onClick(lambdaFunction)
//        button.onClick{lambdaFunction()} // or like this
    }

    var clicksCounter = 0
    val button2 = Button()
    tryToCountButtonClicks2(button2) { clicksCounter++ }
    tryToCountButtonClicks2(button2) { clicksCounter++ }
    println("Button 2 clicks : $clicksCounter")

//    val lambda: () -> Unit = {println("Button has clicked")}


//    -- Lambda as variable
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))

//    Top-level function reference
    run(::salute)

//    Extension functions and reference call
    fun Person.isAdult() = age >= 21
    val predicate = Person::isAdult

//   ---- Bound reference example
    val personArtem = Person("Artem", 42)
    val personAgeFunction = Person::age
    println(personAgeFunction(personArtem))

    val artemAgeFunction = personArtem::age
    println(artemAgeFunction)







}


///////////////////////////// All Functions

fun salute() = println("Salute")

// -- Constructor reference
// !!! can be used only when a class has a single constructor
//val createPerson = ::Person
//val person = createPerson("Person1", 29)

// Lambda has access to method's parameters
fun messageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}

//In Kotlin lambdas can update variables
//Listing 5.11. Changing local variables from a lambda
fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}