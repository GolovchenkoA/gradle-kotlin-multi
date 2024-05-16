package org.example.kotlininaction.chapter6

data class Someone(val name: String)
data class SomeoneElse(val name: String)

fun main(args: Array<String>) {

    println(getStringElvisOperator("abc"))
    println(getStringElvisOperator(null))

    printName(Someone("Someone's name"))
    printName(null)


    printNameIfElse(null)

    println("----- printNameLet")
    printNameLet(null)
    printNameLet(Someone("Someone's name"))

//    printNameOrThrow(null)

//    Examples for  let,with,...  see the file ScopeFunctionsTutorial.


    println("------ as? example")

    val theSame = hasTheSameTypeAndValue(Someone("person1"), SomeoneElse("person1"))
    println("The same: $theSame")


}

fun hasTheSameTypeAndValue(thisClass: Someone, thatClass: Any?): Boolean {
    // This method returns Boolean. if thisClass and thatClass have different types it returns 'false' from the first row
    // otherwise we compare 'name' values
    val otherClass = thatClass as? Someone ?: return false
    return thisClass.name == otherClass.name
}

fun printNameIfElse(someone: Someone?) {
    val name = if (someone == null) null else someone.name
    println("printNameIfElse $name")
}

fun printNameLet(someone: Someone?) {
    someone?.name?.let { println(it) }
}

fun printName(someone: Someone?) {
    println("Someone's name is ${someone?.name}")
}

fun getStringElvisOperator(value: String?): String {
    return value ?: "value is null"
}

fun printNameOrThrow(value: String?): String {
    return value ?: throw IllegalArgumentException("value is null")
}