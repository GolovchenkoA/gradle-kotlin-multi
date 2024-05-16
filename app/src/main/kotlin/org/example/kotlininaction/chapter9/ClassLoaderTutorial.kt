package org.example.kotlininaction.chapter9

import org.example.Service
import java.util.*

fun main(args: Array<String>) {

    // Instance creating using reflection
    val serviceImpl1: ServiceLoader<Service?> = ServiceLoader.load(Service::class.java)

    // Instance creating using 'reified'
    val serviceImpl2: ServiceLoader<Service?> = loadService<Service>()
}

inline fun <reified T> loadService(): ServiceLoader<T?> {
    return ServiceLoader.load(T::class.java)
}