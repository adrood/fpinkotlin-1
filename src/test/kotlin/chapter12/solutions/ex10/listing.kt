/**
 * Try to write 'compose' which composes two Monads. It's not possible,
 * but it is instructive to attempt and understand why this is the case.
 *
 * Tip: this operation hinges on the implementation of the flatMap
 * primitive of the Monad interface.
 */
package chapter12.solutions.ex10

import chapter11.Monad
import chapter12.Composite
import chapter12.CompositeOf
import chapter12.CompositePartialOf

interface Listing<F, G> {

    //tag::init1[]
    fun <F, G> compose(
        mf: Monad<F>,
        mg: Monad<G>
    ): Monad<CompositePartialOf<F, G>> =
        object : Monad<CompositePartialOf<F, G>> {
            override fun <A> unit(a: A): CompositeOf<F, G, A> =
                Composite(mf.unit(mg.unit(a)))

            override fun <A, B> flatMap(
                mna: CompositeOf<F, G, A>,
                f: (A) -> CompositeOf<F, G, B>
            ): CompositeOf<F, G, B> =
                TODO("Simply can't be done!")
        }
    //end::init1[]
}

/**
 * You would need to write 'flatMap' in terms of 'Monad<F>' and 'Monad<G>'
 * which in itself doesn't compile
 */

/*
//tag::init2[]
fun <A, B> flatMap(
    mna: CompositeOf<F, G, A>,
    f: (A) -> CompositeOf<F, G, B>
): CompositeOf<F, G, B> =
        mf.flatMap(mna.fix().value) { na: Kind<G, A> ->
            mg.flatMap(na) { a: A ->
                f(a)
            }
        }
//end::init2[]
*/

/**
 * Here, all you have is 'f', which returns an F<G<B>>. For it to have the
 * appropriate type to return from the argument to 'G.flatMap', you'd
 * need to be able to swap the 'F' and 'G' types. In other words,
 * you'd need some distributive law. Such an operation is not part of the
 * Monad interface.
 */