package org.example.kotlininaction.chapter8

/**
 * Note that the return from the outer function is possible only if the function that takes the lambda as an argument
 * is inlined. In listing 8.19, the body of the forEach function is inlined together with the body of the lambda,
 * so it’s easy to compile the return expression so that it returns from the enclosing function. Using the return
 * expression in lambdas passed to non-inline functions isn’t allowed. A non-inline function can save the lambda passed
 * to it in a variable and execute it later, when the function has already returned, so it’s too late for the lambda
 * to affect when the surrounding function returns.
 */

// https://kotlinlang.org/docs/returns.html#return-to-labels
fun main(args: Array<String>) {
    // How 'return' works
    // The rule is simple: return returns from the closest function declared using the fun keyword.
    // Lambda expressions don’t use the fun keyword, so a return in a lambda returns from the outer function.
    // Anonymous functions do use fun; therefore, in the previous example, the anonymous function is the closest
    // matching function. Consequently, the return expression returns from the anonymous function, not from the
    // enclosing one. The difference is illustrated in figure 8.5.


    val contacts = listOf(Contact("Alice", "Smith"), Contact("Bob", "Smith"))
    println("------------------------ Example inline function with return:")
    findAlice(contacts)
    println("------------------------ Example non-inline function where 'return' is not allowed")
    findAlice2(contacts)
    println("------------------------ Example non-inline function with return to label@")
    findAlice3(contacts)
    println("------------------------ Example Using return in an anonymous function")
    findAlice4(contacts)

//    Anonymous function example.
//    Listing 8.23. Using an anonymous function with filter
    contacts.filter(fun (person): Boolean {
        return person.firstName == "Bob"
    })


    println("------------------------ Example loop with label@")
    loopWithLabel()

    println("------------------------ Example of inner lambdas and this@lable")
    val sb: StringBuilder = StringBuilder().apply sb@{
        // first lambda
        listOf(1,2,3,4,5).apply {
            // second lambda
            println("second lambda call")
            this@sb.append(this.toString()) // here this is the listOf(1,2,3,4,5)
        }
    }
    println(sb.toString())
}

//Top-level functions

fun List<Contact>.forEachNonInline(action: (Contact) -> Unit) {
    for (element in this) {
        action(element)
    }
}

fun findAlice(contacts: List<Contact>) {
    contacts.forEach {
        println("Check ${it.firstName}")
        if(it.firstName == "Alice") {
            println("Alice found!!!!")
            // it actually breaks the loop and return from the caller method (forEach in this case)
            // it works because the 'forEach' is an inline function. For non-inline functions it's not allowed to use 'return'
            return
        }
    }
    println("Alice not found")
}

fun findAlice2(contacts: List<Contact>) {
    contacts.forEachNonInline {
        println("Check ${it.firstName}")
        if(it.firstName == "Alice") {
            println("Alice found!!!")
            // For non-inline functions it's not allowed to use 'return'
//            return
        }
    }
    println("Alice not found")
}

/**
 * Break a loop (for non-inline functions) using @label
 */
fun findAlice3(contacts: List<Contact>) {
//   for contacts.forEachNonInline the code below works as well
    contacts.forEach myTag@{
        if(it.firstName == "Alice") return@myTag
        println("Check ${it.firstName}")
    }
    println("This code actually skips Alice and doesn't print her name")
}
/**
 * Listing 8.22. Using return in an anonymous function
 */
fun findAlice4(contacts: List<Contact>) {
//   for contacts.forEachNonInline the code below works as well
    contacts.forEach (fun (person) {
        if(person.firstName == "Alice") return
        println("Check ${person.firstName}")
    })
    println("This code outside of forEach loop")
}

fun loopWithLabel() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
        print(it)
    }
    println(" done with explicit label")
}
