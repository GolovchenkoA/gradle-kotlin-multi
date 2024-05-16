package org.example.kotlininaction.chapter11

open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString(): String = "<$name> ${children.joinToString(" ")} </$name>"
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init) // calls TABLE.doInit(..)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init) // calls TR.doInit(..)
}

class TD : Tag("td")

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

fun main(args: Array<String>) {
    fun createTable() =
//        createHTML().
        table {
            tr {
                td {
//                    +"cell"
                }
            }
        }

    fun createTable2() =
//        createHTML().
        table {
            for (i in 1..2) {
                tr {
                    td {
//                    +"cell"
                    }
                }
            }
        }

    println(createTable())
    println(createTable2())

//    The same as in the previous example, but it uses tags
//    If you tried to use regular lambdas instead of lambdas with receivers for builders, the syntax would become as
//    unreadable as in this example: you’d have to use the it reference to invoke the tag-creation methods or assign
//    a new parameter name for every lambda. Being able to make the receiver implicit and hide the this reference makes
//    the syntax of builders nice and similar to the original HTML.

//    val createSimpleHTML = createHTML()
//        .table {
//            (this@table).tr {
//                (this@tr).td {
//                    +"cell"
//                }
//            }
//        }

}