/**
 * Write a function that transforms a list of integers by adding 1 to
 * each element. This should be a pure function that returns a new List.
 *
 * Tip: Use foldRight without resorting to recursion.
 */
package chapter3.solutions.sol15

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun increment(xs: List<Int>): List<Int> =
    foldRight(
        xs,
        List.empty(),
        { i: Int, ls ->
            Cons(i + 1, ls)
        })
// end::init[]

class Solution15 : WordSpec({
    "list increment" should {
        "add 1 to every element" {
            increment(
                List.of(1, 2, 3, 4, 5)
            ) shouldBe List.of(2, 3, 4, 5, 6)
        }
    }
})
