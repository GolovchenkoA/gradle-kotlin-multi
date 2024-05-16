package org.example

/**
 * Source: https://github.com/hhariri/oreilly-kotlin-course/blob/master/advanced/src/main/kotlin/com/hadihariri/kotlincourse/delegation/DelegatingMemberFunctions.kt
 */
data class Email(val id: Int, val email: String)

interface Repository {
    fun findById(id: Int): Email
    fun save(email: Email)
}

interface Logger {
    fun log(message: String)
}

class Controller(repository: Repository, logger: Logger): Repository by repository, Logger by logger {
    fun index() {
        findById(1) // repository.findById(..)
        log("log message") // logger.log(..)
    }
}