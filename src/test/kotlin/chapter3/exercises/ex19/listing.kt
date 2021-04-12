/**
 * Write functions flatMap and flatMap2 that work like map except that
 * the function will return a list instead of a single result, and that
 * list should be inserted into the final resulting list.
 * flatMap uses both foldRight and append.
 * flatMap2 only uses foldRight.
 *
 * Tip: Use a combination of existing functions that we have already
 * defined.
 */
package chapter3.exercises.ex19

import chapter3.Cons
import chapter3.List
import chapter3.append
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =

    SOLUTION_HERE()

fun <A, B> flatMap2(xa: List<A>, f: (A) -> List<B>): List<B> =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise19 : WordSpec({
    "list flatmap" should {
        "!map and flatten a list" {
            val xs = List.of(1, 2, 3)
            flatMap(xs) { i -> List.of(i, i) } shouldBe
                List.of(1, 1, 2, 2, 3, 3)

            flatMap2(xs) { i -> List.of(i, i) } shouldBe
                List.of(1, 1, 2, 2, 3, 3)
        }
    }
})
