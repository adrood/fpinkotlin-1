package chapter6.sec2

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

//tag::init1[]
// Listing 6.4.
// A purely functional random number generator, implementing RNG
data class SimpleRNG(val seed: Long) : RNG {
    override fun nextInt(): Pair<Int, RNG> {
        // We use the current seed to generate a new seed.
        // 'and' is a bitwise AND
        val newSeed =
            (seed * 0x5DEECE66DL + 0xBL) and
                0xFFFFFFFFFFFFL // <1>
        // The next state, which is a RNG instance created from
        // the new seed.
        val nextRNG = SimpleRNG(newSeed) // <2>
        // The value n is the new pseudo-random integer.
        // 'ushr' is a right binary shift with zero fill.
        val n = (newSeed ushr 16).toInt() // <3>
        return n to nextRNG // <4>
    }
}
//end::init1[]
