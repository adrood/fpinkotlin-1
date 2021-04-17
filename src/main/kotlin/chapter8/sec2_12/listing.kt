package chapter8.sec2_12

import chapter8.FailedCase
import chapter8.SuccessCount

//tag::init[]
// Section 8.2.5
// Listing 8.3.
// Model the possible results of a test run as an ADT
// Sealed type of Result
sealed class Result { // <1>
    abstract fun isFalsified(): Boolean
}

// Subtype indicates that all tests passed
object Passed : Result() { // <2>
    override fun isFalsified(): Boolean = false
}

// Subtype indicates that one of the test cases falsified
// the property
data class Falsified( // <3>
    val failure: FailedCase,
    val successes: SuccessCount
) : Result() {
    override fun isFalsified(): Boolean = true
}
//end::init[]
