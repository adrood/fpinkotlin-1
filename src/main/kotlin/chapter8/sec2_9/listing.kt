package chapter8.sec2_9

//tag::init[]
// Section 8.2.3
// Listing 8.2.
// Define Gen by wrapping a state transition over a random number
// generator
interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

data class State<S, out A>(val run: (S) -> Pair<A, S>)

data class Gen<A>(val sample: State<RNG, A>)
//end::init[]
