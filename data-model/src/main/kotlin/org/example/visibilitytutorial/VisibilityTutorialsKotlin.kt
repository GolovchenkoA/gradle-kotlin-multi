package org.example.visibilitytutorial

// !!!!!!!!! I couldn't reproduce this behavior
class VisibilityTutorialsKotlin {
    private val kotlinPrivateField = "this field is private but when this class is compiled then " +
            "it becomes package-private for java." +
            "For additional information read O'Reilly Kotlin in action. Kotlin’s visibility modifiers and Java"
}