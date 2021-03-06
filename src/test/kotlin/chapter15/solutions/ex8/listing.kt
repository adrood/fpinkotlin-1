/**
 * Implement a processor for 'exists' that takes a predicate. There are
 * multiple ways to implement this. Given a
 *
 *     Stream.of(1, 3, 5, 6, 7)
 *
 * then
 *
 *     exists { it % 2 == 0 }
 *
 * could:
 *
 * - produce Stream(true): halting and only yielding the final result
 * - produce Stream(false, false, false, true): halting and yielding all
 *   intermediate results
 * - or produce Stream(false, false, false, true, true): not halting, and
 *   yielding all the intermediate results.
 *
 * Note that there is no penalty to implementing the "trimming" of this
 * final form with a separate combinator, because 'pipe' fuses the
 * processors.
 */
package chapter15.solutions.ex8

import chapter10.None
import chapter10.Option
import chapter10.Some
import chapter15.sec2.Await
import chapter15.sec2.Emit
import chapter15.sec2.Halt
import chapter15.sec2.Process
import chapter15.sec2.toList
import chapter3.List
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init1[]
fun <I> exists(f: (I) -> Boolean): Process<I, Boolean> =
    Await { i: Option<I> ->
        when (i) {
            is Some ->
                Emit<I, Boolean>(
                    f(i.get),
                    exists { f(i.get) || f(it) }
                )
            is None -> Halt<I, Boolean>()
        }
    }
//end::init1[]

//tag::init2[]
fun <I> existsAndHalt(f: (I) -> Boolean): Process<I, Boolean> =
    Await { i: Option<I> ->
        when (i) {
            is Some ->
                if (f(i.get)) Emit(true)
                else Emit<I, Boolean>(false, existsAndHalt { f(it) })
            is None -> Halt<I, Boolean>()
        }
    }
//end::init2[]

class Exercise8 : WordSpec({
    "exists" should {
        val stream = Stream.of(1, 3, 5, 6, 7)
        "not halt and yield all intermediate results" {
            val p = exists<Int> { i -> i % 2 == 0 }
            p(stream).toList() shouldBe
                List.of(false, false, false, true, true)
        }
        "halt and yield all intermediate results" {
            val p = existsAndHalt<Int> { i -> i % 2 == 0 }
            p(stream).toList() shouldBe
                List.of(false, false, false, true)
        }
    }
})
