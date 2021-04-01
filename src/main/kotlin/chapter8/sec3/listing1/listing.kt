package chapter8.sec3.listing1

import chapter8.RNG
import chapter8.State

data class Gen<A>(val sample: State<RNG, A>)

//tag::init[]
// Listing 8.6. Representation of a sized generator as a
// function from Int to Gen
data class SGen<A>(val forSize: (Int) -> Gen<A>)
//end::init[]
