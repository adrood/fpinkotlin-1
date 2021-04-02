package chapter6.sec1

import kotlin.random.Random

//tag::init[]
// Method should return a random number from 1 to 6
fun rollDie(): Int { // <1>
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
