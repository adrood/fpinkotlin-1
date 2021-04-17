import chapter6.sec2.SimpleRNG
import chapter6.sec2.RNG
import chapter6.sec4.rollDie
import chapter6.sec6.ns2

//Listing 6.1.
// Using the Kotlin Random class to demonstrate
// mutation of internal state.

val rng0 = kotlin.random.Random

rng0.nextDouble()

rng0.nextDouble()

rng0.nextInt()

rng0.nextInt(10)

// Choose an arbitrary value to initialize SimpleRNG
val rng1 = SimpleRNG(42)
// Destructure the Piar<Int, RNG> returned from nextInt
val (n1, rng2) = rng1.nextInt()
n1
val (n2, rng3) = rng2.nextInt()
n2

// val zero = rollDie(SimpleRNG(5))



