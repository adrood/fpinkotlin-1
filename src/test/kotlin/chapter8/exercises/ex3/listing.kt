/**
 * Assuming the following representation, use check to implement 'and' as
 * a method of Prop
 *
 * An anonymous instance of Prop is returned that is based on this and the
 * property p that is passed in.
 */
package chapter8.exercises.ex3

import utils.SOLUTION_HERE

//tag::init[]
interface Prop {
    fun check(): Boolean
    fun and(p: Prop): Prop =

        SOLUTION_HERE()
}
//end::init[]
