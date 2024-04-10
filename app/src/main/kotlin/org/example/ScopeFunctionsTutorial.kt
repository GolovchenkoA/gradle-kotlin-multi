package org.example

import org.gradle.configurationcache.extensions.capitalized
import java.util.*

/**
 * Source : https://www.youtube.com/watch?v=MHxGv4K6BsM
 */

class PersonV1 {
    var name: String? = null;
    var age: Int? = null;

    override fun toString(): String {
        return "$name - $age"
    }
}

class Account {
    var uuid: String = UUID.randomUUID().toString();
    var owner: String = "account owner"
}

fun main(args: Array<String>) {

    println("------------------------------------ Apply")
    println("Use it to initialize or configure an object")
    // apply keyword
//    Context object: refers to context object using 'this' keyword
//    Returns: context object

    val person1 = PersonV1()
    person1.name = "Sam"
    person1.age = 10

    val person2 = PersonV1().apply {
        this.name = "Sam"
        age = 20
    }
    println("Apply result: $person2")

    println("------------------------------------ Also")
    println("Use it to use additional operations on an object")
    // also keyword
//    Context object: refers to context object using 'it' keyword
//    Returns: context object

    val list = mutableListOf(1, 2)
    list.add(3)
    println(list)
    list.remove(3)
    println(list)

    val theSameList = list.also {
        it.add(3)
        println(it)
        it.remove(3)
        println(it)
    }

    println("Variable are the same")
    println(list.hashCode())
    println(theSameList.hashCode())


    println("------------------------------------ With")
    println("Use it to operate on a non-nullable objects")
    // with keyword
//    Context object: refers to context object using 'this' keyword
//    Returns: lambda result
    val acc = Account()
    val withResult = with(acc) {
        println(uuid)
        println(this.owner)

        42
    }
    println("withResult: $withResult")

    println("------------------------------------ Let")
    println("Use it to execute lambda expressions on nullable objects and avoid NPE")
    // also keyword
//    Context object: refers to context object using 'it' keyword
//    Returns: lambda result
//    !!!!!!!!!!! Use Let to avoid NPE

    val name: String? = null;

//    println(name!!.capitalized()) // throws NPE because !! asserts '
    println(name?.capitalized())
    println(name?.reversed())

    println("Name properties will not be printed because it used by 'let'")
    val letNameResult = name?.let {
        println(it.capitalized())
        println(it.reversed())

        "return from  name?.let "
    }

    println("Let return value: $letNameResult")


    println("------------------------------------ Run")
    println("Use it to operate on a nullable objects, execute lambda expressions and avoid NPE")
    // Run keyword. !!!!!!!!!!!! Combination of 'with' and 'let'
//    Context object: refers to context object using 'this' keyword
//    Returns: lambda result

    val nullablePerson : Person? = null
    println("Person properties will not be printed because it's null and 'run' is used")
    val runNullablePersonResult = nullablePerson?.run {
        println(this.name.reversed())
        println(age)

        "return from  nullablePerson?.run "
    }

    println("run return value: $runNullablePersonResult")

}