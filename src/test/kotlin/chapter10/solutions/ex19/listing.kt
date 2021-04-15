/**
 * A bag is like a set, except that it is represented by a map that
 * contains one entry per element with that element as the key, and the
 * value under that key is the number of times the element appears in
 * the bag. For example:
 *
 *    bag(listOf("a", "rose", "is", "a", "rose")) = {a=2, rose=2, is=1}
 *
 * Use monoids to compute such a bag from a List<A>
 *
 * Tip: Consider using mapMergeMonoid, along with another monoid that was
 * developed earlier in the chapter to achieve this binning.
 */
package chapter10.solutions.ex19

import chapter10.ForList
import chapter10.List
import chapter10.ListOf
import chapter10.asConsList
import chapter10.fix
import chapter10.intAdditionMonoid
import chapter10.mapMergeMonoid
import chapter10.solutions.ex16.Foldable
import io.kotlintest.properties.Gen
import io.kotlintest.properties.assertAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init1[]
object ListFoldable : Foldable<ForList> {

    override fun <A, B> foldRight(
        fa: ListOf<A>,
        z: B,
        f: (A, B) -> B
    ): B =
        fa.fix().foldRight(z, f)

    fun <A> bag(la: List<A>): Map<A, Int> =
        foldMap(la, mapMergeMonoid<A, Int>(intAdditionMonoid)) { a: A ->
            mapOf(a to 1)
        }
}
//end::init1[]

class Exercise19 : WordSpec({
    "bag" should {
        "bin the contents of a list into a map" {
            assertAll(Gen.list(Gen.choose(0, 10))) { ls ->
                val actual = ListFoldable.bag(ls.asConsList())
                val expected =
                    ls.groupBy { it }.mapValues { it.value.count() }
                actual shouldBe expected
            }
        }
    }
})
