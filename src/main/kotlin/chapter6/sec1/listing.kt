package chapter6.sec1

import chapter6.sec2.SimpleRNG
import kotlin.random.Random

//tag::init[]
// Listing 6.2.
// A method simulating the roll of a die with an off-by-one error.
// Method should return a random number from 1 to 6
fun rollDie(): Int { // <1>
    // Creates a new random number generator seeded with
    // the current system time
    val rng = Random
    // Generator returns a random number from 0 to 5
    return rng.nextInt(6) // <2>
}
//end::init[]

//tag::init2[]
fun rollDie2(rng: Random): Int = rng.nextInt(6)
//end::init2[]

//tag::init3[]
interface RNG {
    fun nextInt(): Pair<Int, RNG>
}
//end::init3[]
