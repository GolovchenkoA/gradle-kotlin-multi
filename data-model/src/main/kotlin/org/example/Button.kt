package org.example

class Button {
    fun onClick(function: () -> Unit){
        function.invoke()
    }

//    fun onClick(function: String -> Unit) {
//        TODO("Not yet implemented")
//    }
//    fun onClick(function: () -> Int) {
//        TODO("Not yet implemented")
//    }
}