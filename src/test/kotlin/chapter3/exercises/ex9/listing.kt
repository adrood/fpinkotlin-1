/**
 * Our implementation of foldRight is not tail-recursive and will result
 * in a StackOverflowError for large lists (we say it's not stack-safe).
 * Convince yourself that this is the case (see Exercise 7), and then
 * write onother general list-recursion fucntion, foldLeft, that is
 * tail-recursive, using the techniques we discussed in the previous
 * chapter.
 */
package chapter3.exercises.ex9

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise9 : WordSpec({
    "list foldLeft" should {
        """!apply a function f providing a zero accumulator from tail
            recursive position""" {
            foldLeft(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }
})
