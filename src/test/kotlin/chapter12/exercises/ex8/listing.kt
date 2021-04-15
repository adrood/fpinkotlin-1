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
