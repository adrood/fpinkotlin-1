/**
 * Implement flatMap, and then use it to implement this more dynamic
 * version of ListOfN. Place flatMap and ListOfN in the Gen data class
 * as shown
 *
 * Tip: Try using the previous implementation of ListOfN from Exercise 8.5
 * in addition to flatMap in your solution.
 */
package chapter8.exercises.ex6

import chapter8.RNG
import chapter8.State
import utils.SOLUTION_HERE

//tag::init[]
data class Gen<A>(val sample: State<RNG, A>) {

    companion object {
        fun <A> listOfN(gn: Gen<Int>, ga: Gen<A>): Gen<List<A>> =

            SOLUTION_HERE()
    }

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =

        SOLUTION_HERE()
}
//end::init[]
