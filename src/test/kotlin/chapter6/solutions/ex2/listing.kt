/**
 * Write a function to generate a Double between 0 and 1, not including 1.
 * In addition to the function you already developed, you can use
 * Int.MAX_VALUE to obtain the maximum positive integer value, and you
 * can use x.toDouble() to convert an x: Int to a Double.
 *
 * Tip: Use nonNegativeInt to generate a random integer between 0
 * and Int.MAX_VALUE, inclusive. Then map to the range of doubles
 * from 0 to 1.
 */
package chapter6.solutions.ex2

import chapter6.RNG
import chapter6.solutions.ex1.nonNegativeInt
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init[]
fun double(rng: RNG): Pair<Double, RNG> {
    val (i, rng2) = nonNegativeInt(rng)
    return (i / (Int.MAX_VALUE.toDouble() + 1)) to rng2
}
//end::init[]

class Solution2 : WordSpec({

    "double" should {

        val unusedRng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> = TODO()
        }

        "generate a max value approaching 1 based on Int.MAX_VALUE" {

            val rngMax = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    Int.MAX_VALUE to unusedRng
            }

            double(rngMax) shouldBe (0.9999999995343387 to unusedRng)
        }

        "generate a min value of 0 based on 0" {
            val rngMin = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    0 to unusedRng
            }

            double(rngMin) shouldBe (0.0 to unusedRng)
        }
    }
})
