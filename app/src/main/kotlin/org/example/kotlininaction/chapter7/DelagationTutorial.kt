package org.example.kotlininaction.chapter7

class PersonDelegation {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(name: String, value: String) {
        _attributes[name] = value
    }

//    Direct implementation
//    val name: String
//        get() = _attributes["name"]!!

//    Delegation implementation
//    This works because the standard library defines getValue and setValue extension functions on the standard Map and
//    MutableMap interfaces. The name of the property is automatically used as the key to store the value in the map.
//    As in listing 7.25, p.name hides the call of _attributes.getValue(p, prop), which in turn is implemented
//    as _attributes[prop.name].
    val name: String by _attributes
}


fun main(args: Array<String>) {

    // Example is not added. 7.5.4. Delegated-property translation rules

//    You can customize where the value of the property is stored (in a map, in a database table,
//    or in the cookies of a user session) and also what happens when the property is
//    accessed (to add validation, change notifications, and so on)

//  7.5.5. Storing property values in a map (expando objects)

}