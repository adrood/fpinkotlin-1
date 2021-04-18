/**
 * Implement flatMap in terms of an abstract definition of compose. By
 * this, it seems as though we've found another minimal set of monad
 * combinators: compose and unit.
 *
 * Tip: Consider what effect it would have if we assumed A to be Unit.
 */
package chapter11.solutions.ex8

import arrow.Kind
import chapter11.Functor

interface Monad<F> : Functor<F> {

    fun <A> unit(a: A): Kind<F, A>

    fun <A, B, C> compose(
        f: (A) -> Kind<F, B>,
        g: (B) -> Kind<F, C>
    ): (A) -> Kind<F, C>

    //tag::init[]
    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B> =
        compose<Unit, A, B>({ _ -> fa }, f)(Unit)
    //end::init[]
}
