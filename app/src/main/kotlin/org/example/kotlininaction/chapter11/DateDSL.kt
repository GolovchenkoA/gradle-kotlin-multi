package org.example.kotlininaction.chapter11

import java.time.LocalDate
import java.time.Period

val Int.days : Period
    get() = Period.ofDays(this)

val Period.ago : LocalDate
    get() = LocalDate.now() - this

val Period.fromNow : LocalDate
    get() = LocalDate.now() + this

fun main(args: Array<String>) {
    val yesterday = 1.days.ago
    val tomorrow = 1.days.fromNow
    println(yesterday)
    println(tomorrow)
}