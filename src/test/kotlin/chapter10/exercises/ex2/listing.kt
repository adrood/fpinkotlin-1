/**
 * Give a Monoid instance for combining Option values.
 *
 * Notice that we have a choice in how we implement op. We can compose
 * the options in either order. Both of the implementations satisfy
 * the monoid laws, but they are not equivalent. This is true in general -
 * that is, every monoid has a dual where the op combines things in the
 * opposite order. Monoids like booleanOr and intAddition are equivalent
 * to their duals because their op is commutative as well as associative.
 *
 * Tip: There is more than one implementation that meets the monoid laws
 * in this instance. Consider implementing a dual helper function for
 * Monoid, allowing for the combination of monoids in reverse order to
 * deal with this duality
 */
package chapter10.exercises.ex2

import arrow.core.Option
import chapter10.Monoid
import utils.SOLUTION_HERE

//tag::init1[]
fun <A> optionMonoid(): Monoid<Option<A>> =

    SOLUTION_HERE()
//end::init1[]

//tag::init2[]
fun <A> dual(m: Monoid<A>): Monoid<A> =

    SOLUTION_HERE()
//end::init2[]

fun <A> firstOptionMonoid() = optionMonoid<A>()

fun <A> lastOptionMonoid() = dual(firstOptionMonoid<A>())
