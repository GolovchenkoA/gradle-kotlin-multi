package org.example.utils

import org.example.Person

internal object PersonUtils {
    fun toUpperCase(person: Person): Person = Person(person.name.uppercase(), person.age)
}