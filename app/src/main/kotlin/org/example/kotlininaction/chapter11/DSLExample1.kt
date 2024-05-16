package org.example.kotlininaction.chapter11

class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) {
        body()
    }
}

fun main(args: Array<String>) {
    //    You often want to be able to support both a nested block structure, as shown here, and a flat call structure in
    //    the same API. In other words, you want to allow both of the following:

    //    With such a design, users of the DSL can use the nested block structure when there are multiple items to configure
    //    and the flat call structure to keep the code more concise when there’s only one thing to configure.

    val dependencies = DependencyHandler()
    dependencies.compile("junit:junit:4.11")

//     The full syntax of this call is dependencies.invoke({this.compile(...)}).
    dependencies {
        compile("junit:junit:4.11")
    }
}