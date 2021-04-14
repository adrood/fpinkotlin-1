/**
 * Implement a listOf combinator on Gen that doesn't accept an explicit
 * size and should return an SGen instead of a Gen. The implementation
 * should generate lists of the size provided by the SGen.
 *
 * Tip: Consider using the listOfN function you wrote before.
 */
package chapter8.solutions.ex12

import chapter8.RNG
import chapter8.State

data class SGen<A>(val forSize: (Int) -> Gen<A>)

data class Gen<A>(val sample: State<RNG, A>) {

    companion object {
        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> =
            Gen(State.sequence(List(n) { ga.sample }))
    }

    //tag::init[]
    fun listOf(): SGen<List<A>> =
        SGen { i -> Gen.listOfN(i, this) }
    //end::init[]
}
