/**
 * A function having the same argument and return type is sometimes
 * called an endofunction. Write a monoid for endofunctions.
 *
 * Tip: We are limited in the number of ways we can combine values with
 * op since it should compose functions of type (A) -> A for any choice
 * of A. There is more than one possible implementation for op, but only
 * one for zero.
 */
package chapter10.solutions.ex3

import arrow.core.compose
import chapter10.Monoid

//tag::init1[]
fun <A> endoMonoid(): Monoid<(A) -> A> =
    object : Monoid<(A) -> A> {
        override fun combine(a1: (A) -> A, a2: (A) -> A): (A) -> A =
            { a -> a1(a2(a)) }

        override val nil: (A) -> A
            get() = { a -> a }
    }
//end::init1[]

//tag::init2[]
fun <A> endoMonoidComposed(): Monoid<(A) -> A> =
    object : Monoid<(A) -> A> {
        override fun combine(a1: (A) -> A, a2: (A) -> A): (A) -> A =
            a1 compose a2

        override val nil: (A) -> A
            get() = { it }
    }
//end::init2[]
