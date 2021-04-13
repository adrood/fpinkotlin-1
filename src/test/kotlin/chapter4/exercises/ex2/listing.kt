/**
 * Implement the variance function in terms of flatMap. If the mean of a
 * sequence is m, the variance is the mean of x minus m to the power of 2
 * for each element of x in the sequence. In code, this will be
 *
 *     (x - m).pow(2)
 *
 * The mean method developed in Listing 4.2. may be used to implement this.
 */
package chapter4.exercises.ex2

import chapter3.List
import chapter4.isEmpty
import chapter4.size
import chapter4.sum
import chapter4.None
import chapter4.Option
import chapter4.Some
import chapter4.getOrElse
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlin.math.pow
import utils.SOLUTION_HERE

fun mean(xs: List<Double>): Option<Double> =
    if (xs.isEmpty()) None
    else Some(xs.sum() / xs.size())

//tag::init[]
fun variance(xs: List<Double>): Option<Double> =

    SOLUTION_HERE()
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise2 : WordSpec({

    "variance" should {
        "!determine the variance of a list of numbers" {
            val ls =
                List.of(1.0, 1.1, 1.0, 3.0, 0.9, 0.4)
            variance(ls).getOrElse { 0.0 } shouldBe
                (0.675).plusOrMinus(0.005)
        }
    }
})
