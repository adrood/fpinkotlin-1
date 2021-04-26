/**
 * The definition of 'to' uses a new combinator called 'join'. Any process
 * which wishes to concatenate a nested Process can use this handy
 * function. Implement 'join' using existing primitives. This combinator
 * should be quite familiar to you from previous chapters.
 *
 * Tip: You can flatMap it!
 */
package chapter15.exercises.ex12

import chapter15.sec3_3.Process
import utils.SOLUTION_HERE

//tag::init[]
fun <F, O> join(p: Process<F, Process<F, O>>): Process<F, O> =

    SOLUTION_HERE()
//end::init[]
