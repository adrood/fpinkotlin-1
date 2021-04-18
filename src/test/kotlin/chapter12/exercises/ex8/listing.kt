/**
 * Just like we can take the product of two monoids 'A' and 'B' to give
 * the monoid (A, B), we can take the product of two applicative functors.
 * Implement this function.
 *
 * Tip: See how you can use the provided Product kind as a shim for the
 * Pair that we are expecting.
 */
package chapter12.exercises.ex8

import chapter12.Applicative
import chapter12.Product
import chapter12.ProductOf
import chapter12.ProductPartialOf
import chapter12.fix
import utils.SOLUTION_HERE

//tag::init1[]
fun <F, G> product(
    AF: Applicative<F>,
    AG: Applicative<G>
): Applicative<ProductPartialOf<F, G>> =

    SOLUTION_HERE()
//end::init1[]
