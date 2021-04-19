/**
 * Use the property-based testing framework we developed in Chapter 8
 * to implement properties for the monoid laws of associativity and
 * identity. Use your properties to test some of the monoids we've written
 * so far.
 *
 * Tip: You will need to generate three values of type A for testing the
 * law of associativity.
 */
package chapter10.exercises.ex4

import chapter10.Monoid
import chapter10.intAdditionMonoid
import chapter10.intMultiplicationMonoid
import chapter8.Passed
import chapter8.SimpleRNG
import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//tag::init1[]
fun <A> monoidLaws(m: Monoid<A>, gen: Gen<A>): Prop =

    SOLUTION_HERE()
//end::init1[]

//tag::init2[]
//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({
    val max = 100
    val count = 100
    val rng = SimpleRNG(42)
    val intGen = Gen.choose(-10000, 10000)

    "law of associativity" should {
        "!be upheld using existing monoids" {
            monoidLaws(intAdditionMonoid, intGen)
                .check(max, count, rng) shouldBe Passed

            monoidLaws(intMultiplicationMonoid, intGen)
                .check(max, count, rng) shouldBe Passed
        }
    }
})
//end::init2[]
