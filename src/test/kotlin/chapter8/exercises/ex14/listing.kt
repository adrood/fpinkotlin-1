/**
 * Write a property called maxProp to verify the behavior of List.sorted,
 * which you can use to sort (among other things) a List<Int>.
 */
package chapter8.exercises.ex14

import arrow.core.extensions.list.foldable.exists
import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll
import chapter8.SGen
import chapter8.sec4_1.run
import utils.SOLUTION_HERE

val smallInt = Gen.choose(-10, 10)

fun List<Int>.prepend(i: Int) = listOf(i) + this

fun maxProp(): Prop = SOLUTION_HERE()

fun main() {
    run(maxProp())
}
