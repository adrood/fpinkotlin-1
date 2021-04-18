/**
 * Implement the composition of two monads, where one of them
 * is traversable
 *
 * Tip: You might need to acquire an Applicative<G> from an external scope
 * for use when calling Traversable<F>. This has already been provided
 * for you.
 */
package chapter12.solutions.ex19

import arrow.Kind
import chapter11.Monad
import chapter12.Applicative
import chapter12.Composite
import chapter12.CompositeOf
import chapter12.CompositePartialOf
import chapter12.Traversable
import chapter12.fix

fun <F> applicative() = object : Applicative<F> {
    override fun <A> unit(a: A): Kind<F, A> = TODO()
}

//tag::init[]
fun <G, H, A> composeM(
    MG: Monad<G>,
    MH: Monad<H>,
    AH: Applicative<H>,
    TH: Traversable<H>
): Monad<CompositePartialOf<G, H>> =
    object : Monad<CompositePartialOf<G, H>> {

        override fun <A> unit(a: A): CompositeOf<G, H, A> =
            Composite(MG.unit(MH.unit(a)))

        override fun <A, B> flatMap(
            cgha: CompositeOf<G, H, A>,
            f: (A) -> CompositeOf<G, H, B>
        ): CompositeOf<G, H, B> =
            // Wrap this in a Composite shim to be returned from flatMap
            Composite( // <8>
                // Use the Monad<G> instance to join the adjacent outer
                // Kind<G, ?> layers, resulting in Kind<G, Kind<H,B>
                MG.join( // <7>
                    // Use the Monad<G> to map over the CompositeOf<G,H,A>
                    // value passed into flatMap, discarding the shim and
                    // resulting in Kind<H,B> injected into the function
                    // block.
                    MG.map(cgha.fix().value) { ha -> // <6>
                        // Use the Monad<G> instance to map so the Monad<H>
                        // instance can be applied to the inner Kind<H,?>
                        // layers, resulting in Kind<G,Kind<H,B>>
                        MG.map( // <5>
                            // Use the Traversable<H> instance to sequence
                            // the top two layers resulting in
                            // Kind<G,Kind<H,Kind<H,B>>>
                            TH.sequence( // <4>
                                // Use the Monad<H> instance to map over
                                // this value, stripping off the shim,
                                // resulting in
                                // Kind<H,Kind<G,<Kind<H,B>>>
                                MH.map( // <3>
                                    // Use the Applicative<H> instance to
                                    // apply this lifted function to
                                    // Kind<H,A>, resulting in
                                    // Kind<H,CompositeOf<G,H,B>
                                    AH.apply( // <2>
                                        // Lift function f in a Kind<H,?>
                                        // giving
                                        // Kind<H,(A)->CompositeOf<G,H,B>
                                        AH.unit(f), // <1>
                                        ha
                                    )
                                ) { cghbc ->
                                    // Use the Monad<H> instance to map
                                    // over this value, stripping off the
                                    // shim, resulting in
                                    // Kind<H,Kind<G,<Kind<H,B>>>
                                    cghbc.fix().value // <3>
                                // Use the Traversable<H> instance to sequence
                                // the top two layers resulting in
                                // Kind<G,Kind<H,Kind<H,B>>>
                                }, applicative() // <4>
                            )
                        // Use the Monad<G> instance to map so the Monad<H>
                        // instance can be applied to the inner Kind<H,?>
                        // layers, resulting in Kind<G,Kind<H,B>>
                        ) { MH.join(it) } // <5>
                    }
                )
            )
    }
//end::init[]
