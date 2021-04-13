/**
 * As an example, implement hasSubsequence for checking whether a List
 * contains another List as a subsequence.
 * We'll return to this implementation in Chapter 5 and hopefully
 * improve on it.
 *
 * Tip:
 * It's good to specify some properties about these functions up front.
 * For example, do you expect these assertions to be true?
 *
 * xs.append(ys).startsWith(xs) shouldBe true
 * xs.startsWith(Nil) shouldBe true
 * xs.append(ys.append(zs)).hasSubsequence(ys) shouldBe true
 * xs.hasSubsequence(Nil) shouldBe true
 *
 * You will find that if the answer to any one of these is "yes", then
 * that implies something about the answer to the rest of them.
 */
package chapter3.solutions.sol23

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
tailrec fun <A> startsWith(l1: List<A>, l2: List<A>): Boolean =
    when (l1) {
        is Nil -> l2 == Nil
        is Cons -> when (l2) {
            is Nil -> true
            is Cons ->
                if (l1.head == l2.head)
                    startsWith(l1.tail, l2.tail)
                else false
        }
    }

tailrec fun <A> hasSubsequence(xs: List<A>, sub: List<A>): Boolean =
    when (xs) {
        is Nil -> false
        is Cons ->
            if (startsWith(xs, sub))
                true
            else hasSubsequence(xs.tail, sub)
    }
// end::init[]

class Solution23 : WordSpec({
    "list subsequence" should {
        "determine if a list starts with" {
            startsWith(
                List.of(1, 2, 3),
                List.of(1)
            ) shouldBe true
            startsWith(
                List.of(1, 2, 3),
                List.of(1, 2)
            ) shouldBe true
            startsWith(
                List.of(1, 2, 3),
                List.of(1, 2, 3)
            ) shouldBe true
            startsWith(
                List.of(1, 2, 3),
                List.of(2, 3)
            ) shouldBe false
            startsWith(
                List.of(1, 2, 3),
                List.of(3)
            ) shouldBe false
            startsWith(
                List.of(1, 2, 3),
                List.of(6)
            ) shouldBe false
        }

        "identify subsequences of a list" {
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(1)
            ) shouldBe true
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2)
            ) shouldBe true
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(2, 3)
            ) shouldBe true
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(3, 4)
            ) shouldBe true
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(3, 4, 5)
            ) shouldBe true
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(4)
            ) shouldBe true

            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 4)
            ) shouldBe false
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 3)
            ) shouldBe false
            hasSubsequence(
                List.of(1, 2, 3, 4, 5),
                List.of(2, 4)
            ) shouldBe false
        }
    }
})
