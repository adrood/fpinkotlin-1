/**
 * Write a function that turns each value in a List<Double> into a String.
 * You can use the expression d.toString() to convert some d: Double to a
 * String.
 *
 * Tip: Again, use foldRight without resorting to recursion.
 */
package chapter3.exercises.ex16

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun doubleToString(xs: List<Double>): List<String> =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise16 : WordSpec({
    "list doubleToString" should {
        "!convert every double element to a string" {
            doubleToString(List.of(1.1, 1.2, 1.3, 1.4)) shouldBe
                List.of("1.1", "1.2", "1.3", "1.4")
        }
    }
})
