/**
 * Implement Gen.choose using this representation of Gen. It should
 * generate integers in the range start to stopExclusive. Feel free to
 * use functions you've already written.
 *
 * Tip: Consider using the nonNegativeInt method from Chapter 6 to
 * implement this generator.
 */
package chapter8.exercises.ex4

import chapter8.RNG
import chapter8.State
import chapter8.double
import chapter8.nonNegativeInt
import utils.SOLUTION_HERE

data class Gen<A>(val sample: State<RNG, A>) {
    companion object {
        //tag::init[]
        fun choose(start: Int, stopExclusive: Int): Gen<Int> =

            SOLUTION_HERE()
        //end::init[]
    }
}
