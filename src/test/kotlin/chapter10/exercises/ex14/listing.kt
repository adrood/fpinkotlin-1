/**
 * Recall that we implemented a binary Tree in Chapter 3. Next, implement
 * Foldable<ForTree>. You only need to override foldMap of Foldable to
 * make this work, letting the provided foldLeft and foldRight methods
 * use your new implementation.
 *
 * Note: A foldable version of Tree has been provided in the Chapter 10
 * exercises boilerplate code.
 */
package chapter10.exercises.ex14

import chapter10.Branch
import chapter10.ForTree
import chapter10.Leaf
import chapter10.Monoid
import chapter10.TreeOf
import chapter10.fix
import chapter10.intAdditionMonoid
import chapter10.intMultiplicationMonoid
import chapter10.solutions.ex12.Foldable
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init1[]
object TreeFoldable : Foldable<ForTree>

// Put your implementation here!

//end::init1[]

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({
    "TreeFoldable" should {
        val t = Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))
        "!foldMap" {
            TreeFoldable.foldMap(t, intAdditionMonoid) { it } shouldBe 10
            TreeFoldable.foldMap(
                t,
                intMultiplicationMonoid
            ) { it } shouldBe 24
        }
        "!foldRight" {
            TreeFoldable.foldRight(t, 0, { a, b -> a + b }) shouldBe 10
        }
        "!foldLeft" {
            TreeFoldable.foldRight(t, 1, { a, b -> a * b }) shouldBe 24
        }
    }
})
