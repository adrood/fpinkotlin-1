/**
 * Tip: To define translate, use runFree with Free[Function0, _] as
 * the target monad. The use the specialized runTrampoline function
 * written earlier
 */
package chapter13.exercises.ex4

import arrow.Kind
import chapter13.boilerplate.free.Free
import chapter13.boilerplate.free.FreePartialOf
import chapter13.boilerplate.free.Suspend
import chapter13.boilerplate.free.fix
import chapter13.boilerplate.function.ForFunction0
import chapter13.boilerplate.function.Function0
import chapter13.boilerplate.function.Function0Of
import chapter13.boilerplate.console.Console
import chapter13.sec4.console.ForConsole
import chapter13.sec4.console.fix
import chapter13.sec4_2.Translate
import chapter13.sec4_2.runFree
import chapter13.solutions.ex1.freeMonad
import chapter13.solutions.ex2.runTrampoline
import utils.SOLUTION_HERE

//tag::init1[]
fun <F, G, A> translate(
    free: Free<F, A>,
    translate: Translate<F, G>
): Free<G, A> =

    SOLUTION_HERE()

fun <A> runConsole(a: Free<ForConsole, A>): A =

    SOLUTION_HERE()
//end::init1[]
