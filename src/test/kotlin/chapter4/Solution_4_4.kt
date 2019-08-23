package chapter4

import chapter3.listings.Cons
import chapter3.listings.List
import chapter3.listings.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

fun <A> sequence(xs: List<Option<A>>): Option<List<A>> =
        xs.foldRight(Some(Nil), { oa1: Option<A>, oa2: Option<List<A>> ->
            map2(oa1, oa2) { a1: A, a2: List<A> ->
                Cons(a1, a2)
            }
        })

class SolutionSpec_4_4 : WordSpec({

    "sequence" should {
        "turn a list of some options into an option of list" {
            val lo = List.of(Some(10), Some(20), Some(30))
            sequence(lo) shouldBe Some(List.of(10, 20, 30))
        }
        "turn a list of options containing none into a none" {
            val lo = List.of(Some(10), None, Some(30))
            sequence(lo) shouldBe None
        }
    }
})