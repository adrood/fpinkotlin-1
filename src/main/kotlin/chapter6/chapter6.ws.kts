import chapter6.sec2.SimpleRNG
import chapter6.sec2.RNG
import chapter6.sec6.ns2

val rng = SimpleRNG(42)
val (n1, rng2) = rng.nextInt()
n1
val (n2, rng3) = rng2.nextInt()
n2



