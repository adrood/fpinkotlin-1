/**
 * Restate the monad law of associativity in terms of flatMap using join,
 * map and unit.
 *
 * Tip: Use identity functions where possible to arrive at a reworked
 * solution.
 * Consider expressing your solution using the following type declarations
 * when reworking the laws.
 */
package chapter11.solutions.ex13

import arrow.Kind
import chapter11.Monad

interface Listing<F, A> : Monad<F> {

    //tag::initA[]
    val f: (A) -> Kind<F, A>
    val g: (A) -> Kind<F, A>
    val x: Kind<F, A>
    val y: Kind<F, Kind<F, Kind<F, A>>>
    val z: (Kind<F, Kind<F, A>>) -> Kind<F, Kind<F, A>>
    //end::initA[]

    fun associative() {
        //tag::init1[]
        // We first look at the associative law expressed in terms of
        // flatMap based on the previously established premise:
        flatMap(flatMap(x, f), g) ==
            flatMap(x) { a -> flatMap(f(a), g) }
        //end::init1[]

        //tag::init2[]
        // We can substitute f and g with identity functions and x with
        // a higher kind y, to express this differently:
        flatMap(flatMap(y, z)) { b -> b } ==
            flatMap(y) { a -> flatMap(z(a)) { b -> b } }

        flatMap(flatMap(y, z)) { it } ==
            flatMap(y) { a -> flatMap(a) { it } }
        //end::init2[]

        //tag::init3[]
        // We also know from Exercise 11.12 that join is a flatMap
        // combined with an identity function:
        flatMap(join(y)) { it } ==
            flatMap(y) { join(it) }

        join(join(y)) ==
            flatMap(y) { join(it) }
        //end::init3[]

        //tag::init4[]
        // We have also learned in Exercise 11.3 that flatMap can be
        // expressed as a map and join, this eliminating the final flatMap.
        join(join(y)) ==
            join(map(y) { join(it) })
        //end::init4[]

        //tag::init5[]
        // Lastly, we now substitute occurrences of join(y) with unit(x),
        // which in both cases amounts to Kind<F, A>
        join(unit(x)) ==
            join(map(x) { unit(it) })
        //end::init5[]
    }
}
