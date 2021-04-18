/**
 * Prove that all monads are applicative functors by showing that if the
 * monad laws hold, the 'Monad' implementations of 'map2' and 'map'
 * satisfy the applicative laws. Prove this by using the left identity
 * and right identity applicative laws.
 *
 * Tip: Implement 'map2' in terms of 'flatMap', then that in terms
 * of 'compose'. Start with either identity law in turn, then substitute
 * equals for equals and apply the monad laws until you get an equation
 * that is obviously true.
 */
package chapter12.solutions.ex7

import arrow.Kind
import chapter11.Monad

interface Listing<F, A> : Monad<F> {

    val fa: Kind<F, A>
    val fb: Kind<F, A>
    val f: (A, A) -> Kind<F, A>
    val ka: (A) -> Kind<F, A>

    fun listing1() {
        //tag::init1[]
        // Let's begin with the left identity law first.
        map2(unit(Unit), fa) { _, a -> a }
        //end::init1[]

        //tag::init2[]
        // Considering that the declaration of map2 in terms of
        // flatMap is:
        flatMap(fa) { a -> map(fb) { b -> f(a, b) } }
        //end::init2[]

        //tag::init3[]
        // We can start by substituing 'map2' for its 'flatMap' equivalent:
        flatMap(unit(Unit)) { u -> map(fa) { a -> a } } == fa
        //end::init3[]

        //tag::init4[]
        // We can substitute 'map(fa) { a -> a }' out by applying the
        // functor law
        flatMap(unit(Unit)) { fa } == fa
        //end::init4[]

        //tag::init5[]
        // We can then express 'flatMap' in terms of 'compose' by lifting
        // each kind into its Kleisli equivalent of '(A) -> Kind<F, A>:
        compose({ _: A -> unit(Unit) }, { _ -> fa }) == { _: A -> fa }
        //end::init5[]

        val unit = { a: A -> unit(a) }
        //tag::init6[]
        // But we also know that the left identity law expressed in terms
        // of compose states:
        compose(unit, ka) == ka
        //end::init6[]

        //tag::init7[]
        // Therefore, the lhs of init5 simplifies to '{ _: A -> fa }':
        { _: A -> fa } == { _: A -> fa }
        //end::init7[]

        //tag::init8[]
        // Lastly, we apply any value of 'A' to both sides and we get:
        fa == fa
        //end::init8[]

        //tag::init9[]
        // Now that we have establishec equality on the left, let's shift
        // our attention to the right identity law.
        map2(fa, unit(Unit)) { a, _ -> a }
        //end::init9[]

        //tag::init10[]
        // This side follows along the same lines, except that it is
        // symmetrical:
        flatMap(fa) { a -> map(unit(Unit)) { u -> a } } == fa

        flatMap(fa) { a -> unit(a) } == fa

        compose({ _: A -> fa }, { _: A -> unit(Unit) }) == { _: A -> fa }
        //end::init10[]

        //tag::init11[]
        // We use the right identity law expressed in terms of compose:
        compose(ka, unit) == ka
        //end::init11[]

        //tag::init12[]
        // And finally, we can conclude equality by once again applying
        // any value of A to both sides:
        { _: A -> fa } == { _: A -> fa }

        fa == fa
        //end::init12[]
    }
}
