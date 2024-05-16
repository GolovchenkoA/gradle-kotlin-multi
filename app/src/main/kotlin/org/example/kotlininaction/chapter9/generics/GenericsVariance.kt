package org.example.kotlininaction.chapter9.generics

fun main(args: Array<String>) {

    // !!!!!!! See: StarProjections.kt file
    // https://kotlinlang.org/docs/generics.html#use-site-variance-type-projections

    //Kotlin provides so-called star-projection syntax for this:
    //For Foo<out T : TUpper>, where T is a covariant type parameter with the upper bound TUpper, Foo<*> is equivalent to Foo<out TUpper>. This means that when the T is unknown you can safely read values of TUpper from Foo<*>.
    //For Foo<in T>, where T is a contravariant type parameter, Foo<*> is equivalent to Foo<in Nothing>. This means there is nothing you can write to Foo<*> in a safe way when T is unknown.
    //For Foo<T : TUpper>, where T is an invariant type parameter with the upper bound TUpper, Foo<*> is equivalent to Foo<out TUpper> for reading values and to Foo<in Nothing> for writing values.


    // https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science)
    // Depending on the variance of the type constructor, the subtyping relation of the simple types may be either:
    // 1) preserved for the respective complex types (covariance)
    // 2) reversed for the respective complex types (contrvariant)
    // 3) ignored for the respective complex types (invariant ???)
    // Variance is how subtyping between more complex types relates to subtyping between their components.

    // If a function accepts a read-only list, you can pass a List with a more specific element type.
    // If the list is mutable, you can’t do that.

    // the method is safe because it's read-only
    printContents(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    printContents(listOf("a", "b", "c", "d", "e"))

    // This method is not safe because it is not read-only
    // it’s not safe to pass a MutableList<String> as an argument when a MutableList<Any> is expected;

    // case 1: works. I guess it's just not invoked because the list is not used elsewhere
    addAnswer(mutableListOf("a", "b", "c"))

  // Case 2: This code is not compiled
//    val characters = mutableListOf("a", "b", "c")
//    addAnswer(characters)


    // Classes, types and subtypes
    // List is a class
    // List<String> and List<Int> are types of List
    // B (for example Int) is a subtype of A (for example Any) if we can use B where A is expected
    // If we cannot it's not a subtype (for example Int is not a subtype of String)

    //  The term supertype is the opposite of subtype.
    //  If B is a subtype of A, then A is a supertype of B.

    fun test(i: Int) {
        val n: Number = i
        fun strF(str: String) {
            // do something
        }
        fun numF(num: Number) {
            // do something
        }

//        strF(n) // Is not compiled because Int is not a subtype of String
    numF(i) // compiled because Int is a subtype of Number
    }


    // Subtypes and nullable
    // Int is a subtype of Int?
    // Int? is not a subtype of Int
    val num: Int? = 0 // it's valid because Int is subtype of Int?

}

// the method is safe because it's read only
fun printContents(list: List<Any>) {
    println(list.joinToString())
}

// the method is not safe because it's not read only
// it works, but it is not safe. We can provide List<String> here and get ClassCastException
fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}