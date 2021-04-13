/**
 * Can you write foldLeft in terms of foldRight? How about the other way
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
package chapter3.solutions.sol12

import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> foldLeftR(xs: List<A>, z: B, f: (B, A) -> B): B =
    foldRight(
        xs,
        { b: B -> b },
        { a, g ->
            { b ->
                g(f(b, a))
            }
        })(z)

fun <A, B> foldRightL(xs: List<A>, z: B, f: (A, B) -> B): B =
    foldLeft(xs,
        { b: B -> b },
        { g, a ->
            { b ->
                g(f(a, b))
            }
        })(z)

//expanded example

/**
 * We'll alias the type of this particular identity/delay function
 * Identity<B> so we aren't writing (B) -> B everywhere
 */
typealias Identity<B> = (B) -> B

fun <A, B> foldLeftRDemystified(
    ls: List<A>,
    acc: B,
    combiner: (B, A) -> B
): B {

    val identity: Identity<B> = { b: B -> b }

    val combinerDelayer: (A, Identity<B>) -> Identity<B> =
        { a: A, delayedExec: Identity<B> ->
            { b: B ->
                delayedExec(combiner(b, a))
            }
        }

    val chain: Identity<B> = foldRight(ls, identity, combinerDelayer)

    return chain(acc)
}
// end::init[]

class Solution12 : WordSpec({
    "list foldLeftR" should {
        "implement foldLeft functionality using foldRight" {
            foldLeftR(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
            foldLeftRDemystified(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }

    "list foldRightL" should {
        "implement foldRight functionality using foldLeft" {
            foldRightL(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }
})
