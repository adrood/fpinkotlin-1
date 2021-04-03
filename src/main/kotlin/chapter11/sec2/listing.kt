package chapter11.sec2

import arrow.Kind
import chapter11.ForGen
import chapter11.Gen
import chapter11.Gen.Companion.unit
import chapter11.GenOf
import chapter11.fix
import chapter11.sec1.Functor
import chapter4.Option
import chapter9.Parser

fun <A, B> flatMap(fa: Gen<A>, f: (A) -> Gen<B>): Gen<B> = TODO()

fun <A, B> flatMap(fa: Parser<A>, f: (A) -> Parser<B>): Parser<B> = TODO()

fun <A, B> map(fa: Parser<A>, f: (A) -> B): Parser<B> = TODO()

fun <A, B> flatMap(fa: Option<A>, f: (A) -> Option<B>): Option<B> = TODO()

fun <A, B> map(fa: Option<A>, f: (A) -> B): Option<B> = TODO()

//tag::init6[]

// Listing 11.3.
// Implementations of map2 for Gen, Parser and Option

fun <A, B> map(fa: Gen<A>, f: (A) -> B): Gen<B> =
    flatMap(fa) { a -> unit(f(a)) }
//end::init6[]

//tag::init1[]
fun <A, B, C> map2(
    fa: Gen<A>,
    fb: Gen<B>,
    f: (A, B) -> C
): Gen<C> = //<1>
    // Make a generator of a random C that runs random generators
    // fa and fb, combining their results with the function f
    flatMap(fa) { a -> map(fb) { b -> f(a, b) } }
//end::init1[]

//tag::init2[]
fun <A, B, C> map2(
    fa: Parser<A>,
    fb: Parser<B>,
    f: (A, B) -> C
): Parser<C> = //<2>
    // Make a parser that produces C by combining the results of
    // parsers fa and fb with the function f
    flatMap(fa) { a -> map(fb) { b -> f(a, b) } }
//end::init2[]

//tag::init3[]
fun <A, B, C> map2(
    fa: Option<A>,
    fb: Option<B>,
    f: (A, B) -> C
): Option<C> = //<3>
    // Combines two Options with the function f if both have a value,
    // otherwise returns None
    flatMap(fa) { a -> map(fb) { b -> f(a, b) } }
//end::init3[]

//tag::init4[]
// The Mon interface is parameterized with higher-kinded type of F
interface Mon<F> { // <1>
    //tag::init5[]

    // Listing 11.5.
    // Introducing flatMap and map declarations to the Mon interface

    fun <A, B> map(fa: Kind<F, A>, f: (A) -> B): Kind<F, B>

    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>
    //end::init5[]

    fun <A, B, C> map2(
        // Use Kind<F,A> to represent F<A>
        fa: Kind<F, A>, // <2>
        // Will not compile since map and flatMap are not defined
        // in context of F
        fb: Kind<F, B>, // <3>
        f: (A, B) -> C
    ): Kind<F, C> =
        flatMap(fa) { a -> map(fb) { b -> f(a, b) } } // <4>
}
//end::init4[]

//tag::init7[]

// Listing 11.6.
// Declaration of the Monad with primitives defined for flatMap and unit.

// Monad provides a default implementation of map and can so
// implement Functor

interface Monad<F> : Functor<F> { // <1>

    fun <A> unit(a: A): Kind<F, A>

    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>

    // The override of map in Functor needs to be made explicit
    // for succesful compilation
    override fun <A, B> map(
        fa: Kind<F, A>,
        f: (A) -> B
    ): Kind<F, B> = //<2>
        flatMap(fa) { a -> unit(f(a)) }

    fun <A, B, C> map2(
        fa: Kind<F, A>,
        fb: Kind<F, B>,
        f: (A, B) -> C
    ): Kind<F, C> =
        flatMap(fa) { a -> map(fb) { b -> f(a, b) } }
}
//end::init7[]

//tag::init8[]

// Listing 11.7.
// Declaring a Monad instance for Gen using concrete types

object Monads {

    // The type ForGen is a surrogate type we provide to get around
    // Kotlin's limitations in expressing higher-kinded types
    val genMonad = object : Monad<ForGen> { // <1>

        // The type alias GenOf<A> is syntactic sugar for
        // Kind<ForGen, A>
        override fun <A> unit(a: A): GenOf<A> = Gen.unit(a) // <2>

        override fun <A, B> flatMap(
            fa: GenOf<A>,
            f: (A) -> GenOf<B>
        ): GenOf<B> =
            // Down-cast all GenOf<A> to Gen<A> using provided
            // extension method fix() for compatibility
            // with Gen.flatMap
            fa.fix().flatMap { a: A -> f(a).fix() } // <3>
    }
}
//end::init8[]
