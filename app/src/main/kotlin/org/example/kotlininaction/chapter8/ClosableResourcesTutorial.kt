package org.example.kotlininaction.chapter8

import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {

}

//Listing 8.17. Using the use function for resource management
fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br -> return br.readLine() }
}