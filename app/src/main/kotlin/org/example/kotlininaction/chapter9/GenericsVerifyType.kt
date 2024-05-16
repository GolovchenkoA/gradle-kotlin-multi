package org.example.kotlininaction.chapter9

fun main(args: Array<String>) {
    checkType(listOf(1, 2, 3))


    castCollectionType(listOf(1, 2, 3))


//    castCollectionType(setOf(1, 2, 3)) // trows throw IllegalArgumentException("List is expected")

//    castCollectionType(listOf("a", "b", "c")) //throws ClassCastException class java.lang.String cannot be cast to class java.lang.Number

//    Cause: You don’t get an IllegalArgumentException, because you can’t check whether the argument is a List<Int>.
//    Therefore the cast succeeds, and the function sum is called on such a list anyway. During its execution,
//    an exception is thrown. This happens because the function tries to get Number values from the list and add them
//    together. An attempt to use a String as a Number results in a ClassCastException at runtime.
//    castCollectionType(listOf("a", "b", "c"))


    //Inline + reified
    // !!!!!!!!!!!!! Java\Kotlin
    // Note that inline function with reified type parameters can’t be called from Java code.
    // Normal inline functions are accessible to Java as regular functions—they can be called but aren’t inlined.
    // Functions with reified type parameters require additional processing to substitute the type argument values
    // into the bytecode, and therefore they must always be inlined. This makes it impossible to call them in a regular
    // way, as the Java code does.

    println("inline fun <reified T> examples: ")
    println(isA<String>(1))
    println(isA<String>("a"))


    // reified function example.
    // inline + reified allows to check type of collection members for generics
    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>()) //[one, three]

}




fun <T> checkType(seq: Iterable<T>) {
//    if (seq is List<String>) { // Such check is not allowed because information about type is erased
    if (seq is List<*>) {
        println("This is List")
    }else if (seq is Set<*>) {
        println("This is Set")
    } else {
        println("type is unknown")
    }
}

fun castCollectionType(seq: Collection<*>) {
    // it converts any List regardless of content type. and in this case we get ClassCastException later
    val intList = seq as? List<Int> ?: throw IllegalArgumentException("List is expected") // unchecked cast.
    for (item in intList) {
        print(item)
    }
    println()
}

// //Error: Cannot check for instance of erased type: T
//fun <T> isA(value: Any) = value is T

//it works because of inline + reified
// !!! Here we use inline not for performance optimization (because there is no lambda as parameter)
// Here we use inline to be able to use 'reified'
inline fun <reified T> isA(value: Any) = value is T

