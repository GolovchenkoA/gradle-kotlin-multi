package org.example.kotlininaction.chapter8

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        mySynchronized(lock, body)
    }
}

inline fun <T> mySynchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }

//    in other cases we would use simpler way:    lock.withLock {  }
}

fun main(args: Array<String>) {
    val lock = ReentrantLock()

    LockOwner(lock).runUnderLock {
        println("Code surrounded by Lock")
    }
}