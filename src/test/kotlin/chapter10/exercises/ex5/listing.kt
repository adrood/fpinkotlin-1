/**
 * The function foldMap is used to align the types of the list elements
 * so that a Monoid instance may be applied to the list. Implement this
 * function.
 *
 * Tip: It is possible to map and then concatenate, although this is very
 * inefficient. A single foldLeft can be used instead.
 */
package chapter10.exercises.ex5

import chapter10.sec1.Monoid
import utils.SOLUTION_HERE

//tag::init1[]
fun <A, B> foldMap(la: List<A>, m: Monoid<B>, f: (A) -> B): B =

    SOLUTION_HERE()
//end::init1[]
