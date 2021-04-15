/**
 * Tip: Apply the knowledge gained from section 13.3.1 for dealing with
 * tail-call elimination through reification.
 */
package chapter13.exercises.ex2

import chapter13.boilerplate.free.FlatMap
import chapter13.boilerplate.free.Free
import chapter13.boilerplate.free.Return
import chapter13.boilerplate.free.Suspend
import chapter13.boilerplate.function.ForFunction0
import chapter13.boilerplate.function.fix
import utils.SOLUTION_HERE

@Suppress("UNCHECKED_CAST")
//tag::init1[]
tailrec fun <A> runTrampoline(ffa: Free<ForFunction0, A>): A =

    SOLUTION_HERE()
//end::init1[]
