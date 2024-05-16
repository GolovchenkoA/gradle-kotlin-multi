package org.example.programmingdslinkotlin.chapter6

object schedule {
    infix fun meeting(block: Meeting.() -> Unit) {
        try {
            print(Meeting().apply(block).validate())
        } catch (ex: Exception) {
            println(ex)
        }
    }
}

class Meeting {
    private val errors = mutableListOf<String>()

    fun validate(): Meeting {
        if (startTime.isEmpty()) {
            errors.add("start time not set")
        }

        if (endTime.isEmpty()) {
            errors.add("end time not set")
        }

        //more checking...

        if (!errors.isEmpty()) {
            throw RuntimeException(
                """
        |Meeting not set up properly:
        |${errors.joinToString(System.lineSeparator())}""".trimMargin()
            )
        }

        return this
    }

    //...rest of the code in this class

    var meetingName = ""
    var startTime = IntRange.EMPTY
    var endTime = IntRange.EMPTY
    var scheduledOn = ""
    var attending = ""
    val assign = this
    val starts = Starts()
    val ends = Ends()
    val on = On()
    val participants = Participants()

    infix fun name(name: String) {
        meetingName = name
    }

    inner class Starts {
        infix fun at(time: IntRange) {
            if (!startTime.isEmpty()) {
                errors.add("duplicate start time")
            }

            //more checking...

            startTime = time
        }
    }

    inner class Ends {
        infix fun at(time: IntRange) {
            endTime = time
        }
    }

    inner class On {
        infix fun date(day: Int) = DateCreator(day, this@Meeting)
    }

    class DateCreator(val day: Int, val meeting: Meeting) {
        private fun setScheduledOn(month: Int, year: Int) {
            meeting.scheduledOn =
                java.time.LocalDate.of(year, month, day).toString()
        }

        infix fun January(year: Int) = setScheduledOn(1, year)
        infix fun February(year: Int) = setScheduledOn(2, year)
        infix fun March(year: Int) = setScheduledOn(3, year)
        //... April, May,... December
    }

    inner class Participants {
        infix fun include(name: String): Participants {
            attending = name
            return this
        }

        infix fun and(name: String): Participants {
            attending = "$attending, $name"
            return this
        }
    }

    override fun toString() = """Meeting: $meetingName
  |Starts at ${startTime.start}:${startTime.endInclusive}
  |Ends at ${endTime.start}:${endTime.endInclusive}
  |On $scheduledOn
  |Participants: ${attending}
  """.trimMargin()
}

fun main(args: Array<String>) {
    schedule meeting {
        assign name "Meeting to discuss why meetings aren't effective"
        starts at 14..30
        starts at 14..15
        on date 15 March 2020
        participants include "Sara" and "Jake" and "Mani"
    }
}