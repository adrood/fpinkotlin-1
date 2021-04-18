/**
 * Using the following values, prove that the left and right identity laws
 * expressed in terms of compose are equivalent to those stated in terms
 * of flatMap
 *
 * Tip: Substitute each occurrence of compose by flatMap, then apply
 * value v of type A to both sides of each equation.
 */
package chapter11.exercises.ex9

import arrow.Kind
import chapter11.Monad

interface Listing<F, A> : Monad<F> {

    //tag::init0[]
    val f: (A) -> Kind<F, A>
    val x: Kind<F, A>
    val v: A
    //end::init0[]

    fun listing() {

        //tag::initl1[]
        compose(f, { a: A -> unit(a) })(v) == f(v)
        compose({ a: A -> unit(a) }, f)(v) == f(v)
        //end::initl1[]

        TODO("express in terms of flatMap using substitution")

        //tag::init2[]
        flatMap(x) { a -> unit(a) } == x
        flatMap(unit(v), f) == f(v)
        //end::init2[]
    }
}
