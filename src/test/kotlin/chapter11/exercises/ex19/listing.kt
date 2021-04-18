/**
 * To cement your understanding of monads, give a monad instance for the
 * Reader data type and explain what it means. Also, take some time to
 * answer the following questions:
 * - What are its primitive operations?
 * - What is the action of flatMap?
 * - What meaning does it give to monadic functions like sequence,
 *   join, and replicateM?
 * - What meaning does it give to the monadic laws?
 *
 * Tip: This monad is very similar to the State monad, except that it is
 * "read-only". You can "ask" from it, but not "set" the R value that
 * flatMap carries along.
 */
package chapter11.exercises.ex19

import arrow.Kind
import arrow.Kind2
import arrow.core.extensions.list.foldable.foldLeft
import chapter11.sec2.Monad
import utils.SOLUTION_HERE

fun <A> prefixWith(la: List<A>, prefix: String): List<String> =
    SOLUTION_HERE()

fun main() {
    println(prefixWith(listOf(1, 2, 3, 4, 5), "before"))
}
