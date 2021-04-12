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
package chapter3.exercises.ex23

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::startsWith[]
/**
 * As an extra hint, the exercise file suggests starting by implementing
 * the following function:
 */
tailrec fun <A> startsWith(l1: List<A>, l2: List<A>): Boolean =

    SOLUTION_HERE()
// end::startsWith[]

// tag::init[]
/**
 * Implementing hasSubsequence will be much easier using startsWith
 */
tailrec fun <A> hasSubsequence(xs: List<A>, sub: List<A>): Boolean =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise23 : WordSpec({
    "list subsequence" should {
        "!determine if a list starts with" {
            val xs = List.of(1, 2, 3)
            startsWith(xs, List.of(1)) shouldBe true
            startsWith(xs, List.of(1, 2)) shouldBe true
            startsWith(xs, xs) shouldBe true
            startsWith(xs, List.of(2, 3)) shouldBe false
            startsWith(xs, List.of(3)) shouldBe false
            startsWith(xs, List.of(6)) shouldBe false
        }

        "!identify subsequences of a list" {
            val xs = List.of(1, 2, 3, 4, 5)
            hasSubsequence(xs, List.of(1)) shouldBe true
            hasSubsequence(xs, List.of(1, 2)) shouldBe true
            hasSubsequence(xs, List.of(2, 3)) shouldBe true
            hasSubsequence(xs, List.of(3, 4)) shouldBe true
            hasSubsequence(xs, List.of(3, 4, 5)) shouldBe true
            hasSubsequence(xs, List.of(4)) shouldBe true

            hasSubsequence(xs, List.of(1, 4)) shouldBe false
            hasSubsequence(xs, List.of(1, 3)) shouldBe false
            hasSubsequence(xs, List.of(2, 4)) shouldBe false
        }
    }
})
