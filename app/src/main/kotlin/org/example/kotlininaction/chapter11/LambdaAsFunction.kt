package org.example.kotlininaction.chapter11

/**
 * When you invoke a lambda as a function, the operation is translated into a call of the invoke method, thanks to the
 * convention. Why might that be useful to know? It gives you a way to split the code of a complex lambda into multiple
 * methods while still allowing you to use it together with functions that take parameters of a function type.
 * To do so, you can define a class that implements a function type interface. You can specify the base interface either
 * as an explicit FunctionN type or, as shown in the following listing, using the shorthand syntax: (P1, P2) -> R.
 * This example uses such a class to filter a list of issues by a complex condition.
 *
 * Listing 11.17. Extending a function type and overriding invoke()
 *
 *  The advantage of this approach is that the scope of methods you extract from the lambda body is as narrow as possible;
 *  they’re only visible from the predicate class. This is valuable when there’s a lot of logic both in the predicate
 *  class and in the surrounding code and it’s worthwhile to separate the different concerns cleanly.
 */

enum class IssuePriority {LOW, STANDARD, HIGH}

data class Issue(val id: Int, val project: String, val priority: IssuePriority)

// Class implements functional interface an can be used as Lambda
class ImportantIssuePredicate(val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.priority == IssuePriority.HIGH
    }
}

// Just Functional interface is not enough. It's required  ": (Issue) -> Boolean"
interface IssuePredicate: (Issue) -> Boolean  {
    override fun invoke(issue: Issue): Boolean
}
//@FunctionalInterface
//fun interface IssuePredicate {
//    fun invoke(issue: Issue): Boolean
//}


class NotImportantIssuePredicate(val project: String) : IssuePredicate {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.priority == IssuePriority.LOW
    }
}

fun main(args: Array<String>) {
    val issue1 = Issue(1, "Project A", IssuePriority.LOW)
    val issue2 = Issue(2, "Project A", IssuePriority.HIGH)
    val issue3 = Issue(3, "Project B", IssuePriority.STANDARD)
    val importantIssue = ImportantIssuePredicate("Project A")
    val notImportantIssue = NotImportantIssuePredicate("Project A")
    val issueList = listOf(issue1, issue2, issue3)
    issueList.filter(importantIssue).forEach {println("Important issue: $it")}
    issueList.filter(notImportantIssue).forEach {println("Not Important issue: $it")}

//  filtering in place
    issueList.filter { it.priority == IssuePriority.STANDARD }.forEach {println("Standard issue: $it")}

    // using anonymous function
    val standardIssues: (Issue) -> Boolean = fun(issue) = issue.priority == IssuePriority.STANDARD
    issueList.filter(standardIssues).forEach {println("Standard issue 2: $it")}

    // using anonymous class
    issueList.filter(object: IssuePredicate {
        override fun invoke(issue: Issue): Boolean {
            return issue.priority == IssuePriority.STANDARD
        }
    }).forEach {println("Standard issue 3: $it")}
}