package org.example.kotlininaction.chapter9.generics

fun main(args: Array<String>) {
    // Contrvariant
    // https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science)
    // https://kotlinlang.org/docs/generics.html#variance-and-wildcards-in-java

    // A class that is contravariant on the type parameter is a generic class (let’s consider Consumer<T> as an example)
    // for which the following holds: Consumer<A> is a subtype of Consumer<B> if B is a subtype of A. The type arguments
    // A and B changed places, so we say the subtyping is reversed. For example, Consumer<Animal> is a subtype of Consumer<Cat>.

    // contravariance - can be used when we only need to write safely
    // contravariance, and you can only call methods that take String as an argument on List<? super String>
    // (for example, you can call add(String) or set(int, String)). If you call something that returns T in List<T>,
    // you don't get a String, but rather an Object.

    // On the other hand, "function from Animal to String" is a subtype of "function from Cat to String" because the
    // function type constructor is contravariant in the parameter type. Here, the subtyping relation of the simple types
    // is reversed for the complex types.

    val objectArr = arrayOf<Any>("a", "b", "c")
    val charSeq: Array<CharSequence> = arrayOf("a", "b", "c")
    val objectStr = arrayOf<String>("a", "b", "c")
    val objectInt = arrayOf<Int>(1, 2)
    fillRestrictDest(objectArr, "objectArr")
    fillRestrictDest(charSeq, "charSeq")
    fillRestrictDest(objectStr, "objectStr")
//    fillRestrictDest(objectStr, objectInt) // Forbidden!!!!!

}

// Java example
//fun fillRestrictDest(dest: Array<? super String>, value: String) {
fun fillRestrictDest(dest: Array<in String>, value: String) {
    val sb = StringBuilder()
    sb.append(dest)
    sb.append(value)
    println(sb.toString())
}