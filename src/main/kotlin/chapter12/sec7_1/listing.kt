package chapter12.sec7_1

import arrow.Kind
import arrow.higherkind
import chapter10.Monoid
import chapter12.Functor

interface Traversable1<F> : Functor<F> {
    //tag::init1[]
    fun <G, A, B> traverse(
        fa: Kind<F, A>,
        AP: Applicative<G>,
        f: (A) -> Kind<G, B>
    ): Kind<G, Kind<F, B>>
    //end::init1[]
        = TODO()
}

//tag::init2[]
typealias ConstInt<A> = Int
//end::init2[]

interface Traversable2<F> : Functor<F> {
    //tag::init3[]
    fun <A, B> traverse(fa: Kind<F, A>, f: (A) -> Int): Int
    //end::init3[]
        = TODO()
}

interface Foldable<F> {
    //tag::init4[]
    fun <A, B> foldMap(fa: Kind<F, A>, m: Monoid<B>, f: (A) -> B): B
    //end::init4[]
        = TODO()
}

/*
//tag::init5[]
typealias Const<M, A> = M
//end::init5[]
 */

interface Applicative<F> : Functor<F> {
    fun <A> unit(a: A): Kind<F, A>
    fun <A, B, C> map2(
        fa: Kind<F, A>,
        fb: Kind<F, B>,
        f: (A, B) -> C
    ): Kind<F, C>
}

//tag::init6[]

// Listing 12.13
// A shim can be used to bridge the inability to generalize a type
// constructor

@higherkind
data class Const<M, out A>(val value: M) : ConstOf<M, A>
//end::init6[]

//tag::init7[]

// Listing 12.14.
// Turn a Monoid into an Applicative using a partially applied type
// constructor shim

fun <M> monoidApplicative(m: Monoid<M>): Applicative<ConstPartialOf<M>> =
    object : Applicative<ConstPartialOf<M>> {
        //tag::ignore[]
        override fun <A, B> map(
            fa: ConstOf<M, A>,
            f: (A) -> B
        ): ConstOf<M, B> = TODO()
        //end::ignore[]

        override fun <A> unit(a: A): ConstOf<M, A> = Const(m.nil) // <1>

        override fun <A, B, C> map2(
            ma: ConstOf<M, A>,
            mb: ConstOf<M, B>,
            f: (A, B) -> C // <2>
        ): ConstOf<M, C> =
            Const(m.combine(ma.fix().value, mb.fix().value)) // <3>
    }
//end::init7[]

//tag::init8[]

// Listing 12.15.
// Traversable extends Functor and Foldable to implement foldMap
// in terms of traverse

// Traversable now implements both Functor and Foldable
interface Traversable<F> : Functor<F>, Foldable<F> { // <1>

    fun <G, A, B> traverse(
        fa: Kind<F, A>,
        AP: Applicative<G>,
        f: (A) -> Kind<G, B>
    ): Kind<G, Kind<F, B>>

    override fun <A, M> foldMap(
        fa: Kind<F, A>,
        m: Monoid<M>,
        f: (A) -> M
    ): M =
        traverse(fa, monoidApplicative(m)) { a ->
            // Wrap the transformed result in a Const shim
            Const<M, A>(f(a)) // <2>
            // Downcast the kind to Const and extract its value
        }.fix().value // <3>
}
//end::init8[]
