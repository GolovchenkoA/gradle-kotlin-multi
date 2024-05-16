package org.example.kotlininaction.chapter8

data class Contact(val firstName: String, val lastName: String, val phone: String? = null)

class ContactListFilter {
    var prefixString = ""
    var onlyWithPhoneNumbers = false

    fun getPredicate(): (Contact) -> Boolean {
        val startWithPrefix = { contact: Contact ->
            contact.firstName.startsWith(prefixString) || contact.lastName.startsWith(prefixString)
        }

        return if (onlyWithPhoneNumbers) {
            return {
                startWithPrefix(it) && it.phone != null
            }
        } else {
            startWithPrefix
        }
    }
}