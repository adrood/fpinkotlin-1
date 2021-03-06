/**
 * Not surprisingly, SGen at a minimum supports many of the same operations
 * as Gen, and the implementations are rather mechanical. Define some
 * convenience functions on SGen that simply delegate to the corresponding
 * functions on Gen. Also provide a convenient way of invoking an SGen.
 */
package chapter8.exercises.ex11

import chapter8.RNG
import chapter8.State
import utils.SOLUTION_HERE

data class Gen<A>(val sample: State<RNG, A>) {

    fun <B> map(f: (A) -> B): Gen<B> =
        Gen(sample.map(f))

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
        Gen(sample.flatMap { a -> f(a).sample })
}

//tag::init[]
data class SGen<A>(val forSize: (Int) -> Gen<A>) {

    operator fun invoke(i: Int): Gen<A> =

        SOLUTION_HERE()

    fun <B> map(f: (A) -> B): SGen<B> =

        SOLUTION_HERE()

    fun <B> flatMap(f: (A) -> Gen<B>): SGen<B> =

        SOLUTION_HERE()
}
//end::init[]
