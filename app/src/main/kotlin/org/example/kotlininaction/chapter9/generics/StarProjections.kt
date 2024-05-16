package org.example.kotlininaction.chapter9.generics

fun main(args: Array<String>) {
    // Star-projections
    // https://kotlinlang.org/docs/generics.html#star-projections
    // Sometimes you want to say that you know nothing about the type argument, but you still want to use it in a safe way.
    // The safe way here is to define such a projection of the generic type, that every concrete instantiation of that
    // generic type will be a subtype of that projection.

    //Kotlin provides so-called star-projection syntax for this:
    //For Foo<out T : TUpper>, where T is a covariant type parameter with the upper bound TUpper, Foo<*> is equivalent to Foo<out TUpper>. This means that when the T is unknown you can safely read values of TUpper from Foo<*>.
    //For Foo<in T>, where T is a contravariant type parameter, Foo<*> is equivalent to Foo<in Nothing>. This means there is nothing you can write to Foo<*> in a safe way when T is unknown.
    //For Foo<T : TUpper>, where T is an invariant type parameter with the upper bound TUpper, Foo<*> is equivalent to Foo<out TUpper> for reading values and to Foo<in Nothing> for writing values.

    // If a generic type has several type parameters, each of them can be projected independently.
    // For example, if the type is declared as interface Function<in T, out U> you could use the following star-projections:
    //Function<*, String> means Function<in Nothing, String>.
    //Function<Int, *> means Function<Int, out Any?>.
    //Function<*, *> means Function<in Nothing, out Any?>.

    // From "Kotlin In Action"
    // First, note that MutableList<*> isn’t the same as MutableList<Any?>
    // (it’s important here that MutableList<T> is invariant on T). A MutableList<Any?> is a list that you know can contain
    // elements of any type. On the other hand, a Mutable-List<*> is a list that contains elements of a specific type,
    // but you don’t know what type it is. The list was created as a list of elements of a specific type, such as
    // String (you can’t create a new ArrayList<*>), and the code that created it expects that it will only contain elements of that type.
    // Because you don’t know what the type is, you can’t put anything into the list, because any value you put
    // there might violate the expectations of the calling code. But it’s possible to get the elements from the list,
    // because you know for sure that all values stored there will match the type Any?, which is the supertype of all Kotlin types:


//    You can use the star-projection syntax when the information about type arguments isn’t important:
//    you don’t use any methods that refer to the type parameter in the signature, or you only read the data
//    and you don’t care about its specific type. For instance, you can implement the printFirst function
//    taking List<*> as a parameter:

    println("call fun printFirst(collection: Collection<*>)")
    val anyList: List<Any> = listOf("Any anyList String", 2)
    val numbers = listOf(100, 200)
    val strings = listOf("One", "Two")

    printFirst(anyList)
    printFirst(numbers)
    printFirst(strings)
}

fun printFirst(collection: Collection<*>) {
    if (!collection.isEmpty()) {
        println(collection.first())
    }
}