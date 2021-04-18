/**
 * The 'apply' method is useful for implementing 'map3', 'map4', and so
 * on, and the pattern is straightforward. Implement 'map3' and 'map4'
 * using only the 'unit' and 'apply' functions. Note that given
 * f: (A, B) -> C, f.curried() has type (A) -> (B) -> C. These handy
 * curried extension methods are provided by Arrow on functions up to
 * arity 22 in the arrow-syntax module.
 *
 * Tip: Look at your implementation of 'map2' in terms of 'apply' and try
 * to follow the same pattern.
 */
package chapter12.exercises.ex3

import arrow.Kind
import arrow.syntax.function.curried
import chapter12.Functor
import utils.SOLUTION_HERE

interface Applicative<F> : Functor<F> {

    fun <A, B> apply(
        fab: Kind<F, (A) -> B>,
        fa: Kind<F, A>
    ): Kind<F, B>

    fun <A> unit(a: A): Kind<F, A>

    //tag::init1[]
    fun <A, B, C, D> map3(
        fa: Kind<F, A>,
        fb: Kind<F, B>,
        fc: Kind<F, C>,
        f: (A, B, C) -> D
    ): Kind<F, D> =

        SOLUTION_HERE()

    fun <A, B, C, D, E> map4(
        fa: Kind<F, A>,
        fb: Kind<F, B>,
        fc: Kind<F, C>,
        fd: Kind<F, D>,
        f: (A, B, C, D) -> E
    ): Kind<F, E> =

        SOLUTION_HERE()
    //end::init1[]
}
