/**
 * Define nonEmptyListOf for generating nonempty lists, and then update
 * your specification of max to use this generator.
 *
 * Tip: You could use listOfN one more time.
 */
package chapter8.exercises.ex13

import arrow.core.extensions.list.foldable.exists
import chapter8.Gen
import chapter8.Prop
import chapter8.SGen
import chapter8.sec4_1.run
import kotlin.math.max
import utils.SOLUTION_HERE

fun main() {
    //tag::init1[]
    fun <A> nonEmptyListOf(ga: Gen<A>): SGen<List<A>> =

        SOLUTION_HERE()
    //end::init1[]

    val smallInt = Gen.choose(-10, 10)

    //tag::init2[]
    fun maxProp(): Prop =

        SOLUTION_HERE()
    //end::init2[]
    run(maxProp())
}
