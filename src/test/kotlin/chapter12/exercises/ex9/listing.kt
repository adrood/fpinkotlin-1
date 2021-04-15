/**
 * Tip: Use the provided Composite shim to produce the correct output.
 */
package chapter12.exercises.ex9

import arrow.Kind
import chapter12.Applicative
import chapter12.Composite
import chapter12.CompositeOf
import chapter12.CompositePartialOf
import chapter12.fix
import utils.SOLUTION_HERE

//tag::init1[]
fun <F, G> compose(
    AF: Applicative<F>,
    AG: Applicative<G>
): Applicative<CompositePartialOf<F, G>> =

    SOLUTION_HERE()
//end::init1[]
