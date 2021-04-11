/**
 * Cah you write foldLeft in terms of foldRight? How about the other way
 * around? Implementing foldRight via foldLeft is useful because it lets
 * us implement foldRight tail-recursively, which means it works even for
 * large lists without overflowing the stack.
 *
 * Note: This exercise is pushing you well beyond what you currently know,
 * so don't be too hard on yourself if you can't figure this one out yet!
 *
 * Tip: It is certainly possible to do both directions. For foldLeft in
 * terms of foldRight, you should build up, using foldRight, some value
 * that you can use to achieve the effect of foldLeft. This won't be
 * necessarily be the B of the return type but could be a function of
 * signature (B) -> B, also known as Identity in category theory.
 */
package chapter3.exercises.ex12

import chapter3.List
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A, B> foldLeftR(xs: List<A>, z: B, f: (B, A) -> B): B =

    SOLUTION_HERE()

fun <A, B> foldRightL(xs: List<A>, z: B, f: (A, B) -> B): B =

    SOLUTION_HERE()
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise12 : WordSpec({
    "list foldLeftR" should {
        "!implement foldLeft functionality using foldRight" {
            foldLeftR(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }

    "list foldRightL" should {
        "!implement foldRight functionality using foldLeft" {
            foldRightL(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }
})
