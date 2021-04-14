/**
 * Not surprisingly, SGen at a minimum supports many of the same operations
 * as Gen, and the implementations are rather mechanical. Define some
 * convenience functions on SGen that simply delegate to the corresponding
 * functions on Gen. Also provide a convenient way of invoking an SGen.
 */
package chapter8.solutions.ex11

import chapter8.RNG
import chapter8.State

data class Gen<A>(val sample: State<RNG, A>) {

    fun <B> map(f: (A) -> B): Gen<B> =
        Gen(sample.map(f))

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
        Gen(sample.flatMap { a -> f(a).sample })
}

//tag::init[]
data class SGen<A>(val forSize: (Int) -> Gen<A>) {

    operator fun invoke(i: Int): Gen<A> = forSize(i)

    fun <B> map(f: (A) -> B): SGen<B> =
        SGen<B> { i -> forSize(i).map(f) }

    fun <B> flatMap(f: (A) -> Gen<B>): SGen<B> =
        SGen<B> { i -> forSize(i).flatMap(f) }
}
//end::init[]
