/**
 * Let's see what else we can implement using this representation of Gen.
 * Try implementing unit, boolean, and ListOfN with the following
 * signatures, once again drawing on functions previously written.
 *
 * This solution draws heavily on the State API that was developed in
 * Chapter 6. We have hinged our solution on the State.sequence() function,
 * which is able to convert a List<State<S, A>> into a State<A, List<A>>.
 * When applying the list containing n and the wrapped sample to this
 * state transition, we get back a new State which can subsequently be
 * wrapped up again as a new Gen
 *
 * Tip: We can draw heavily on the State API that we developed in Chapter 6
 * for this exercise. We had a method that could provide random boolean
 * values that might come in handy for our boolean() generator. Could we
 * also reuse State.sequence() somehow?
 */
package chapter8.exercises.ex5

import chapter8.RNG
import chapter8.State
import chapter8.nextBoolean
import utils.SOLUTION_HERE

data class Gen<A>(val sample: State<RNG, A>) {
    companion object {

        //tag::init[]
        fun <A> unit(a: A): Gen<A> =

            SOLUTION_HERE()

        fun boolean(): Gen<Boolean> =

            SOLUTION_HERE()

        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> =

            SOLUTION_HERE()
        //end::init[]
    }
}
