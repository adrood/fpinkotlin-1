/**
 * Monadic combinators can be expressed in another minimal set, namely
 * map, unit and join. Implement the join combinator in terms of flatMap.
 */
package chapter11.solutions.ex11

import arrow.Kind
import chapter11.Functor

interface Monad<F> : Functor<F> {

    fun <A> unit(a: A): Kind<F, A>

    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>

    override fun <A, B> map(fa: Kind<F, A>, f: (A) -> B): Kind<F, B> =
        flatMap(fa) { a -> unit(f(a)) }

    //tag::init[]
    fun <A> join(mma: Kind<F, Kind<F, A>>): Kind<F, A> =
        flatMap(mma) { ma -> ma }
    //end::init[]
}
