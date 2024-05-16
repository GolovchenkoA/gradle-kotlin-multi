package org.example.kotlininaction.chapter11

/////////////////////////////////////////////////1st DSL example///////////
interface Matcher<T> {
    fun test(value: T)
}

// Note that in regular code, you’d capitalize the name of the startWith class, but DSLs often require you to deviate from standard naming conventions.
class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix))
            throw AssertionError("String $value does not start with $prefix")
    }
}

infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

/////////////////////////////////////////////////2nd DSL example///////////
object start
object end

infix fun String.should(x: start): StartWrapper = StartWrapper(this)
infix fun String.should(x: end): EndWrapper = EndWrapper(this)

class StartWrapper(val value: String) {
    infix fun with(prefix: String) =
        if (!value.startsWith(prefix)) {
            throw AssertionError(
                "String does not start with $prefix: $value")
        } else {
            //NOP
        }

}

class EndWrapper(val value: String) {
    infix fun with(prefix: String) =
        if (!value.endsWith(prefix)) {
            throw AssertionError(
                "String does not end with $prefix: $value")
        } else {
            //NOP
        }

}

fun main(args: Array<String>) {

    /////////////////////////////////////////////////1st DSL example///////////
    "kotlin" should startWith("kot")
//    "s" should startWith("kot") // Throws AssertionError

    /////////////////////////////////////////////////2nd DSL example///////////
//    Listing 11.22. Chaining calls in the kotlintest DSL
    "kotlin" should start with "kot"
    "kotlin" should end with "lin"
    // "kotlin".should(start).with("kot")
}