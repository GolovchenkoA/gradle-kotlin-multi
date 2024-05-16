package org.example

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(numbers.filter { it % 2 == 0 })

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 }.map { it.name })

    //------------ Example of function call optimization.
//    The 'filter' function executes only 1 time for any size of collection
    val maxAge = people.maxBy(Person::age).age
    // and the result is used in the other lambda below
    people.filter { it.age == maxAge }

    // Function executes Log(n^2) times (people.size * people.size)
//     In our case it's called 4 times for 2 person.
//    people.filter { it.age == people.maxBy(Person::age).age }
    people.filter { it.age == people.maxBy({p ->
        println("call people.maxBy for ${p.name}")
        p.age
    }).age }


    // 5.2.2. “all”, “any”, “count”, and “find”: applying a predicate to a collection

    val canBeInClub27 = { p: Person -> p.age <= 27 }
    val allPeople = listOf(Person("Alice", 27), Person("Bob", 31))
    println(allPeople.all(canBeInClub27)) // false

    println(people.any(canBeInClub27)) // true

    // !people.all {it.age == 27} is the same as people.any {it.age != 27}

//   ---- 5.2.4. flatMap and flatten: processing elements in nested collections
//    'map' function and .toList does the same as .flatMap
//    The flatMap function does two things: At first it transforms (or maps) each element to a collection according
//    to the function given as an argument, and then it combines (or flattens) several lists into one.
//    An example with strings illustrates this concept well (see figure 5.6):

class Book(val title: String, val authors: List<String>)
//    books.flatMap {it.authors}.toSet() //get unique authors for all books

//   FlatMap  Example 2
    val strings = listOf("abc", "def")
//    "abc" -> ["a","b","c"]
    val characters = strings.flatMap { it.toList() } // [a, b, c, d, e, f]

//    Use 'flatten' if we don't need to transform vales of a list
//    listOfLists.flatten().


    // 5.3. Lazy collection operations: sequences
//     !!! Kotlin Sequences is the same as Java Streams
//    In the previous section, you saw several examples of chained collection functions, such as map and filter. These functions create intermediate collections eagerly, meaning the intermediate result of each step is stored in a temporary list. Sequences give you an alternative way to perform such computations that avoids the creation of intermediate temporary objects.

//It will create two lists: one to hold the results of the filter function and another for the results of map.
// This isn’t a problem when the source list contains two elements, but it becomes much less efficient if you have a million.
people.map(Person::name).filter { it.startsWith("A") }

// To make this more efficient, you can convert the operation so it uses sequences instead of using collections directly:
// In this example, no intermediate collections to store the elements are created, so performance for a large number of elements will be noticeably better.
    people.asSequence()
        .map(Person::name).filter { it.startsWith("A") }
        .toList()


//    -- Generate Sequence

    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum()) // Sequence is evaluated only when 'numbersTo100.sum()' is called



    // Functional interfaces
    //  functional interfaces, or SAM interfaces, where SAM stands for single abstract method. The Java API is full of functional
    //  interfaces like Runnable and Callable,

//    -- Implement Runnable interface in place
//    !!!! this example will be create an instance of Runnable for every call. See below how it can be optimized
  postproneExecution(1000, object: Runnable { override fun run() {
      println("Runnable instance is created for every call")
  } })

    val runnable = Runnable { println("Runnable instance is created only once and is reused later") }
    postproneExecution(2000, runnable)

    handleComputenation("This function creates a Runnable instance every time because it has a reference to external the variable (id: String)")

}

fun handleComputenation(id: String) {
    postproneExecution(2000) { println(id) }
}

fun postproneExecution(timeout: Int, runnable: Runnable) {
    runnable.run()
}

