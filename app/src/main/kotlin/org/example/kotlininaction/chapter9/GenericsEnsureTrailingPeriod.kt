package org.example.kotlininaction.chapter9

fun main(args: Array<String>) {
    val sb = StringBuilder("Hello World")
    ensureTrailingPeriod(sb)
    println(sb)
}

fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}
