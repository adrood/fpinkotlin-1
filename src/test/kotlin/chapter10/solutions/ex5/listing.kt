/**
 * The function foldMap is used to align the types of the list elements
 * so that a Monoid instance may be applied to the list. Implement this
 * function.
 *
 * Tip: It is possible to map and then concatenate, although this is very
 * inefficient. A single foldLeft can be used instead.
 */
package chapter10.solutions.ex5

import arrow.core.extensions.list.foldable.foldLeft
import chapter10.sec1.Monoid

//tag::init1[]
fun <A, B> foldMap(la: List<A>, m: Monoid<B>, f: (A) -> B): B =
    la.foldLeft(m.nil, { b, a -> m.combine(b, f(a)) })
//end::init1[]
