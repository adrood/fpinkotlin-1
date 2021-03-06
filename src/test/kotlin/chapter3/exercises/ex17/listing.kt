/**
 * Write a function map that generalizes modifying each element in a list
 * while maintaining the structure of the list. Use the foldRightL variant
 * that uses foldLeft in order to prevent large lists from blowing the
 * stack.
 *
 * Tip: Once more, use foldRight without resorting to recursion.
 */
package chapter3.exercises.ex17

import chapter3.Cons
import chapter3.List
import chapter3.foldRightL
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise17 : WordSpec({
    "list map" should {
        "!apply a function to every list element" {
            map(List.of(1, 2, 3, 4, 5)) { it * 10 } shouldBe
                List.of(10, 20, 30, 40, 50)
        }
    }
})
