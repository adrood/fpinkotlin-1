/**
 * Hard/Optional
 *
 * Use foldMap as developed in Exercise 10.7 to detect ascending order
 * of a List<Int>. This will require some creativity when deriving the
 * appropriate Monoid instance.
 *
 * Tip: Try creating a data type which tracks the interval of the values
 * in a given segment, as well as whether an "unordered segment" has been
 * found. When merging the values of two segments, think about how these
 * two pieces of information should be updated.
 */
package chapter10.exercises.ex9

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//tag::init1[]
fun ordered(ints: Sequence<Int>): Boolean =

    SOLUTION_HERE()
//end::init1[]

//TODO: Enable tests by removing `!` prefix
class Exercise9 : WordSpec({
    "ordered using balanced fold" should {
        "!verify ordering ordered list" {
            ordered(sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9)) shouldBe true
        }

        "!fail verification of unordered list" {
            ordered(sequenceOf(3, 2, 5, 6, 1, 4, 7, 9, 8)) shouldBe false
        }
    }
})
