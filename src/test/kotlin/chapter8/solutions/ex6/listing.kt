/**
 * Implement flatMap, and then use it to implement this more dynamic
 * version of ListOfN. Place flatMap and ListOfN in the Gen data class
 * as shown
 *
 * Tip: Try using the previous implementation of ListOfN from Exercise 8.5
 * in addition to flatMap in your solution.
 */
package chapter8.solutions.ex6

import chapter8.RNG
import chapter8.State

//tag::init[]
data class Gen<A>(val sample: State<RNG, A>) {

    companion object {
        //tag::ignore[]
        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> =
            Gen(State.sequence(List(n) { ga.sample }))
        //end::ignore[]
        fun <A> listOfN(gn: Gen<Int>, ga: Gen<A>): Gen<List<A>> =
            gn.flatMap { n -> listOfN(n, ga) }
    }

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
        Gen(sample.flatMap { a -> f(a).sample })
}
//end::init[]
