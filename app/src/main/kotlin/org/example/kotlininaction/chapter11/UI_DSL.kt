package org.example.kotlininaction.chapter11

class Context{
    lateinit var title: String
    lateinit var message: String
}

class Activity {

}

//fun Context.alert(
//    message: String,
//    title: String,
//    init: AlertDialogBuilder.() -> Unit
//) {
//    this.message = message
//    this.title = title
//}
//
//class AlertDialogBuilder {
//    fun positiveButton(text: String, callback: DialogInterface.() -> Unit)
//    fun negativeButton(text: String, callback: DialogInterface.() -> Unit)
//    // ...
//}
//
//class DialogInterface {
//    fun invoke()
//}
//
//fun Activity.showAreYouSureAlert(process: () -> Unit) {
//    alert(title = "Are you sure?",
//        message = "Are you really sure?") {
//        positiveButton("Yes") { process() }
//        negativeButton("No") { cancel() }
//    }
//}


fun main(args: Array<String>) {

}