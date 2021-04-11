/**
 * Hard/Optional
 *
 * Also implement a parallel version of foldMap called parFoldMap using
 * the library we developed in Chapter 7
 *
 * Tip: Consider the case of a partial answer. We need to know if what we
 * have seen so far is ordered when we have seen some of the elements.
 * For an ordered sequence, every new element seen should not fall
 * within the range of elements seen already.
 */
package chapter10.exercises.ex8

import chapter10.Monoid
import chapter10.stringMonoid
import chapter7.sec4_4.Par
import chapter7.sec4_4.map2
import chapter7.sec4_4.splitAt
import chapter7.sec4_4.unit
import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.specs.WordSpec
import org.awaitility.Awaitility.await
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicReference
import utils.SOLUTION_HERE

//tag::init1[]
fun <A> par(m: Monoid<A>): Monoid<Par<A>> =

    SOLUTION_HERE()

fun <A, B> parFoldMap(
    la: List<A>,
    pm: Monoid<Par<B>>,
    f: (A) -> B
): Par<B> =

    SOLUTION_HERE()
//end::init1[]

//TODO: Enable tests by removing `!` prefix
class Exercise8 : WordSpec() {

    val es = Executors.newFixedThreadPool(4)

    val result = AtomicReference("not updated")

    override fun afterTest(testCase: TestCase, result: TestResult) =
        es.shutdown()

    init {
        "balanced folding parForMap" should {
            "!fold a list in parallel" {
                //tag::init2[]
                parFoldMap(
                    listOf("lorem", "ipsum", "dolor", "sit"),
                    par(stringMonoid), // <3>
                    { it.toUpperCase() }
                )(es).invoke { cb -> result.set(cb) } // <4>
                //end::init2[]

                await().until {
                    result.get() == "LOREMIPSUMDOLORSIT"
                }
            }
        }
    }
}
