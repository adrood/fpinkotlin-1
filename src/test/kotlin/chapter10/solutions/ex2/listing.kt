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
package chapter10.solutions.ex2

import arrow.core.None
import arrow.core.Option
import arrow.core.orElse
import chapter10.Monoid

//tag::init1[]
fun <A> optionMonoid(): Monoid<Option<A>> = object : Monoid<Option<A>> {

    override fun combine(a1: Option<A>, a2: Option<A>): Option<A> =
        a1.orElse { a2 }

    override val nil: Option<A> = None
}

fun <A> dual(m: Monoid<A>): Monoid<A> = object : Monoid<A> {

    override fun combine(a1: A, a2: A): A = m.combine(a2, a1)

    override val nil: A = m.nil
}

fun <A> firstOptionMonoid() = optionMonoid<A>()

fun <A> lastOptionMonoid() = dual(firstOptionMonoid<A>())
//end::init1[]
