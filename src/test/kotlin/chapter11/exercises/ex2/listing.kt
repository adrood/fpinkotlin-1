/**
 * State looks like it could be a monad too, but it takes two type
 * arguments, namely S and A. You need a type constructor of only one
 * argument to implement Monad. Try to implement a State monad, see what
 * issues you run into, and think about if of how you can solve this.
 * We'll discuss the solution later in this chapter.
 *
 * Tip: Since State is a binary type constructor, we need to partially
 * apply it with the S type argument, much like you would do with a
 * partially applied function. Thus, it is not just one monad, but an
 * entire family of monads, one for each type S. Consider devising a way
 * to capture the type S in a type-level scope, and prividing a partially
 * State type in that scope. This should be possible using Arrow's
 * Kind2 interface.
 */
package chapter11.exercises.ex2

import arrow.Kind
import arrow.Kind2
import chapter11.sec2.Monad
import chapter11.solutions.ex2.StateOf

//tag::init[]
data class State<S, out A>(val run: (S) -> Pair<A, S>) : StateOf<S, A>
//end::init[]
