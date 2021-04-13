/**
 * Tip: Use matching and recursion to solve this problem. Consider all
 * the following scenario's in your solution:
 * - What should the function do if the n argument is 0?
 * - What should it do if the list is empty?
 * - What if the list is not empty and n is nonzero?
 */
package chapter3.solutions.sol3

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

// tag::init[]
tailrec fun <A> drop(l: List<A>, n: Int): List<A> =
    if (n == 0) l
    else when (l) {
        is Cons -> drop(l.tail, n - 1)
        is Nil -> throw IllegalStateException(
            "Cannot drop more elements than in list"
        )
    }
// end::init[]

class Solution3 : WordSpec({
    "list drop" should {
        "drop a given number of elements within capacity" {
            drop(List.of(1, 2, 3, 4, 5), 3) shouldBe List.of(4, 5)
        }

        "drop a given number of elements up to capacity" {
            drop(List.of(1, 2, 3, 4, 5), 5) shouldBe Nil
        }

        """throw an illegal state exception when dropped elements
            exceed capacity""" {
                shouldThrow<IllegalStateException> {
                    drop(List.of(1, 2, 3, 4, 5), 6)
                }
            }
    }
})
