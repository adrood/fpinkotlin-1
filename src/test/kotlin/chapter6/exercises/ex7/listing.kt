/**
 * If you can combine two RNG transitions, you should be able to combine
 * a whole list of them. Implement sequence for combining a List of
 * transitions into a single transition. Use it to reimplement the ints
 * function you wrote before. For the sake of simplicity in this exercise,
 * it is acceptable to write ints with recursion to build a list with x
 * repeated n times.
 *
 * Tip: You need to recursively iterate over the list. Remember that you
 * can use foldLeft or foldRight instead of writing a recursive
 * definition. You can also reuse the map2 function you just wrote. As
 * a test case for your implementation, we expect
 *
 *     sequence(List.of(unit(1), unit(2), unit(3)))(r).first
 *
 * to return List(1, 2, 3)
 */
package chapter6.exercises.ex7

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldRight
import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.solutions.ex6.map2
import chapter6.unit
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise7 : WordSpec({

    //tag::init[]
    fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> =

        SOLUTION_HERE()
    //end::init[]

    //tag::init2[]
    fun <A> sequence2(fs: List<Rand<A>>): Rand<List<A>> =

        SOLUTION_HERE()
    //end::init2[]

    fun ints2(count: Int, rng: RNG): Pair<List<Int>, RNG> = SOLUTION_HERE()

    "sequence" should {

        "!combine the results of many actions using recursion" {

            val combined: Rand<List<Int>> =
                sequence(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }

        """!combine the results of many actions using
            foldRight and map2""" {

            val combined2: Rand<List<Int>> =
                sequence2(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined2(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }
    }

    "ints" should {
        "!generate a list of ints of a specified length" {
            ints2(4, rng1).first shouldBe
                List.of(1, 1, 1, 1)
        }
    }
})
