package chapter6.sec5

import chapter6.sec1.RNG

//tag::init1[]

// Listing 6.12.
// Generalized version of map combinator

fun <S, A, B> map(
    sa: (S) -> Pair<A, S>,
    f: (A) -> B
): (S) -> Pair<B, S> = TODO()
//end::init1[]

// Listing 6.13.
// Generalized State type alias for state transition

typealias State0<S, A> =  (S) -> Pair<A, S>

//tag::init2[]

// Listing 6.14.
// Wrapping the state transition in a data class

data class State<S, out A>(val run: (S) -> Pair<A, S>)
//end::init2[]

// Listing 6.15.
// The Rand type alias updated to use State

typealias Rand<A> = State<RNG, A>

// Exercise 6.10.