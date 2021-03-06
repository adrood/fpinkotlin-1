/**
 * Try to write 'compose' which composes two Monads. It's not possible,
 * but it is instructive to attempt and understand why this is the case.
 *
 * Tip: this operation hinges on the implementation of the flatMap
 * primitive of the Monad interface.
 */
package chapter12.exercises.ex10

import chapter11.Monad
import chapter12.Composite
import chapter12.CompositeOf
import chapter12.CompositePartialOf
import utils.SOLUTION_HERE

interface Listing<F, G> {

    //tag::init1[]
    fun <F, G> compose(
        mf: Monad<F>,
        mg: Monad<G>
    ): Monad<CompositePartialOf<F, G>> =

        SOLUTION_HERE()
    //end::init1[]
}
