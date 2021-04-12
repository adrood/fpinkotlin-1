/**
 * Write a function that concatenates a list of lists into a single list.
 * Its runtime should be linear in the total length of all lists. Try to
 * use functions we have already defined.
 *
 * Tip: The foldRight function that we previously defined will work here.
 * In concat2, the append function may be used.
 */
package chapter3.exercises.ex14

import chapter3.Cons
import chapter3.List
import chapter3.append
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A> concat(lla: List<List<A>>): List<A> =

    SOLUTION_HERE()

fun <A> concat2(lla: List<List<A>>): List<A> =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({
    "list concat" should {
        "!concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
