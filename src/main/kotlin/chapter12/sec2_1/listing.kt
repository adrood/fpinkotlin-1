package chapter12.sec2_1

import arrow.Kind
import chapter12.Cons
import chapter12.Functor
import chapter12.List

//tag::init1[]

// Listing 12.1.
// The Applicative interface is defined by having map2 and unit as
// primitives

interface Applicative<F> : Functor<F> {

    fun <A, B, C> map2(
        fa: Kind<F, A>,
        fb: Kind<F, B>,
        f: (A, B) -> C
    ): Kind<F, C>

    fun <A> unit(a: A): Kind<F, A>

    /**
     * The map combinator from Functor is implemented in terms
     * of unit and map2.
     */
    override fun <A, B> map(
        fa: Kind<F, A>,
        f: (A) -> B
    ): Kind<F, B> = // <1>
        // The unit primitive is invoked with dummy value Unit
        map2(fa, unit(Unit)) { a, _ -> f(a) } // <2>

    /**
     * The traverse combinator remains unchanged.
     */
    fun <A, B> traverse(
        la: List<A>,
        f: (A) -> Kind<F, B>
    ): Kind<F, List<B>> = // <3>
        la.foldRight(
            unit(List.empty<B>()),
            { a: A, acc: Kind<F, List<B>> ->
                map2(f(a), acc) { b: B, lb: List<B> -> Cons(b, lb) }
            }
        )
}
//end::init1[]

// Exercises 12.1, 12.2 and 12.3