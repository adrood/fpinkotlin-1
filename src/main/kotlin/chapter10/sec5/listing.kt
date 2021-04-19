package chapter10.sec5

import arrow.Kind
import chapter10.sec1.Monoid

val ints = listOf(1, 2, 3, 4)
val listing = {
    //tag::init0[]
    ints.foldRight(0) { a, b -> a + b }
    //end::init0[]
}

//tag::init1[]

// Something like Foldable is a higher-order type constructor or
// a higher-kinded type.

// The interface declares type F that represents any container
interface Foldable<F> { // <1>

    // The Kind <F,A> represents  the kind of F<A>
    fun <A, B> foldRight(fa: Kind<F, A>, z: B, f: (A, B) -> B): B // <2>

    fun <A, B> foldLeft(fa: Kind<F, A>, z: B, f: (B, A) -> B): B

    fun <A, B> foldMap(fa: Kind<F, A>, m: Monoid<B>, f: (A) -> B): B

    fun <A> concatenate(fa: Kind<F, A>, m: Monoid<A>): A =
        foldLeft(fa, m.nil, m::combine)
}
//end::init1[]

// Exercises 10.12 to 10.16