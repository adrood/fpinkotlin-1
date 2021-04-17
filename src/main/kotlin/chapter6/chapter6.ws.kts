import chapter6.sec2.SimpleRNG
import chapter6.sec2.RNG
import chapter6.sec6.ns2

//Listing 6.1.
// Using the Kotlin Random class to demonstrate
// mutation of internal state.

val rng = kotlin.random.Random

rng.nextDouble()

rng.nextDouble()

rng.nextInt()

rng.nextInt(10)

// val rng = SimpleRNG(42)
// val (n1, rng2) = rng.nextInt()
// n1
// val (n2, rng3) = rng2.nextInt()
// n2



