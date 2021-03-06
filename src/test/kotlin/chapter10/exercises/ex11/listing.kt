/**
 * Use the WC monoid to implement a function that counts words in a
 * String by recursively splitting into substrings and counting the words
 * in those substrings.
 */
package chapter10.exercises.ex11

import chapter10.foldMap
import chapter10.solutions.ex10.Part
import chapter10.solutions.ex10.Stub
import chapter10.solutions.ex10.WC
import chapter10.solutions.ex10.wcMonoid
import io.kotlintest.properties.Gen
import io.kotlintest.properties.assertAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlin.math.min
import utils.SOLUTION_HERE

//tag::init1[]
fun wordCount(s: String): Int =

    SOLUTION_HERE()
//end::init1[]

//TODO: Enable tests by removing `!` prefix
class Exercise11 : WordSpec({

    val words: List<String> =
        "lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"
            .split(" ")

    "word count" should {
        "!count words using balanced folding" {
            assertAll(Gen.list(Gen.from(words))) { ls ->
                val text = ls.joinToString(" ")
                println("${ls.size}: $text")
                wordCount(text) shouldBe ls.size
            }
        }
    }
})
