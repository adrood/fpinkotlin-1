/**
 * Tip: The Free data type is marked as @higherkind, and so has a generated
 * FreePartialOf<F>. This can be used to handle the partially applied
 * type when defining the FreeMonad instance. Refer back to the
 * StateMonad in Chapter 11 for an example of how this can be achieved.
 */
package chapter13.exercises.ex1

import chapter11.Monad
import chapter13.boilerplate.free.FlatMap
import chapter13.boilerplate.free.Free
import chapter13.boilerplate.free.FreeOf
import chapter13.boilerplate.free.FreePartialOf
import chapter13.boilerplate.free.Return
import chapter13.boilerplate.free.fix
import utils.SOLUTION_HERE

//tag::init1[]
fun <F, A, B> Free<F, A>.flatMap(f: (A) -> Free<F, B>): Free<F, B> =

    SOLUTION_HERE()

fun <F, A, B> Free<F, A>.map(f: (A) -> B): Free<F, B> =

    SOLUTION_HERE()
//end::init1[]

//tag::init2[]
fun <F> freeMonad(): Monad<FreePartialOf<F>> =

    SOLUTION_HERE()
//end::init2[]
