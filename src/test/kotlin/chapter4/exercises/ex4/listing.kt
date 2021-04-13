/**
 * Write a function, sequence, that combines a list of Options into one
 * Option containing a list of all the Some values in the original list.
 * If the original list contains None even once, the result of the function
 * should be None; otherwise should be Some with a list of all the values.
 *
 * Tip: Break the list out using matching where there will be a recursive
 * call to sequence in the Cons case. Alternatively, use the foldRight
 * method to take care of the recursion for you.
 * Note: The solution only shows the case of using the foldRight method.
 */
package chapter4.exercises.ex4

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter4.foldRight
import chapter4.None
import chapter4.Option
import chapter4.Some
import chapter4.map2
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//tag::init[]
fun <A> sequence(xs: List<Option<A>>): Option<List<A>> =

    SOLUTION_HERE()
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    "sequence" should {
        "!turn a list of some options into an option of list" {
            val lo =
                List.of(Some(10), Some(20), Some(30))
            sequence(lo) shouldBe Some(List.of(10, 20, 30))
        }
        "!turn a list of options containing none into a none" {
            val lo =
                List.of(Some(10), None, Some(30))
            sequence(lo) shouldBe None
        }
    }
})
