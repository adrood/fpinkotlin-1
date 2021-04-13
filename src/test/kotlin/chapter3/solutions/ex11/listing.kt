/**
 * Write a function that returns the reverse of a list (given List(1,2,3)
 * it returns List(3,2,1). See if you can write it using a fold.
 */package chapter3.solutions.sol11

import chapter3.Cons
import chapter3.List
import chapter3.foldLeft
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> reverse(xs: List<A>): List<A> =
    foldLeft(xs, List.empty(), { t: List<A>, h: A -> Cons(h, t) })
// end::init[]

class Solution11 : WordSpec({
    "list reverse" should {
        "reverse list elements" {
            reverse(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(5, 4, 3, 2, 1)
        }
    }
})
