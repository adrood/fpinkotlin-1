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
package chapter11.solutions.ex2

import arrow.Kind
import arrow.Kind2
import chapter11.sec2.Monad

//tag::init[]
data class State<S, out A>(val run: (S) -> Pair<A, S>) : StateOf<S, A>

sealed class ForState private constructor() {
    companion object
}

typealias StateOf<S, A> = Kind2<ForState, S, A>

fun <S, A> StateOf<S, A>.fix() = this as State<S, A>

typealias StatePartialOf<S> = Kind<ForState, S>

interface StateMonad<S> : Monad<StatePartialOf<S>> {

    override fun <A> unit(a: A): StateOf<S, A>

    override fun <A, B> flatMap(
        fa: StateOf<S, A>,
        f: (A) -> StateOf<S, B>
    ): StateOf<S, B>
}
//end::init[]
