package org.example.kotlininaction.chapter9

// a type parameter with no upper bound specified will have the upper bound of Any?
class Processor<T> {
    fun process(value: T) {
        val v = value?.hashCode() // this value should be checked for null
        println(v)
    }
}

// T:Any means null-values are not allowed
class Processor2<T:Any> {
    fun process(value: T) {
        val v = value.hashCode() // there is no need to check for null
        println(v)
    }
}

fun main(args: Array<String>) {
    val stringProcessor = Processor<String?>()
    val stringProcessor2 = Processor2<String>()
    stringProcessor.process(null)
    stringProcessor.process("abc")

//    stringProcessor2.process(null) // it will not be compiled
}