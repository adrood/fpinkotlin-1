/**
 * Write the implementation of map2 based on the following signature. This
 * function takes two actions, ra and rb, and a function f for combining
 * their results, and returns a new action that combines them.
 *
 * Tip: Start by accepting an RNG. Note that you have a choice in which RNG
 * to pass to which function, and in what order. Think about what you
 * expect the behavior to be, and whether your implementation meets that
 * expectation.
 */
package chapter6.solutions.ex6

import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.unit
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init[]
fun <A, B, C> map2(ra: Rand<A>, rb: Rand<B>, f: (A, B) -> C): Rand<C> =
    { r1: RNG ->
        val (a, r2) = ra(r1)
        val (b, r3) = rb(r2)
        f(a, b) to r3
    }
//end::init[]

class Solution6 : WordSpec({
    "map2" should {

        "combine the results of two actions" {

            val combined: Rand<String> =
                map2(unit(1.0), unit(1), { d, i ->
                    ">>> $d double; $i int"
                })

            combined(rng1).first shouldBe ">>> 1.0 double; 1 int"
        }
    }
})
