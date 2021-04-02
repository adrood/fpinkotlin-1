package chapter6.sec5

//tag::init1[]
// Listing 6.12.
// Generalized version of map combinator
fun <S, A, B> map(
    sa: (S) -> Pair<A, S>,
    f: (A) -> B
): (S) -> Pair<B, S> = TODO()
//end::init1[]

//tag::init2[]
// Listing 6.14.
// Wrapping the state transition in a data class
data class State<S, out A>(val run: (S) -> Pair<A, S>)
//end::init2[]
