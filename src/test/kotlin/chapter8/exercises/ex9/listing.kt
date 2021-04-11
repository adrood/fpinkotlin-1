/**
 * Now that we have a representation of Prop, implement and and or for
 * composing Prop values. Notice that in the case of an or failure, we
 * don't know which property was responsible, the left or the right. Can
 * you devise a way of handling this?
 *
 * We have introduced a tag method to add metadata about a left failure
 * when an or condition is encountered and computation must continue. We
 * mark or tag the property the failure message if Falsified before
 * proceeding to the right side of the or condition. This is very simple
 * but does the trick for now.
 *
 * Tip: Determining which property was responsible for the failure could
 * be achieved by allowing Prop values to tag or label the messages that
 * are propagated on failure.
 */
package chapter8.exercises.ex9

import chapter8.RNG
import utils.SOLUTION_HERE

typealias TestCases = Int

sealed class Result {
    abstract fun isFalsified(): Boolean
}

object Passed : Result() {
    override fun isFalsified(): Boolean = false
}

typealias SuccessCount = Int
typealias FailedCase = String

data class Falsified(
    val failure: FailedCase,
    val successes: SuccessCount
) : Result() {
    override fun isFalsified(): Boolean = true
}

//tag::init[]
data class Prop(val run: (TestCases, RNG) -> Result) {
    fun and(p: Prop): Prop =

        SOLUTION_HERE()

    fun or(p: Prop): Prop =

        SOLUTION_HERE()
}
//end::init[]
