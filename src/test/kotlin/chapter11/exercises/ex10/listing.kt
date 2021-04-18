/**
 * Prove that the identity laws hold for the Option monad.
 *
 * Tip: We should again consider both 'None' and 'Some' cases, and expand
 * the left and right side of the equation for each. The monadic 'unit'
 * can be expressed as
 *
 *     { a: A -> Some(a) }
 *
 * or the briefer { Some(it) } if you prefer it.
 */
package chapter11.exercises.ex10

import arrow.core.ForOption
import arrow.core.None
import arrow.core.Some
import chapter11.Monad

interface Listing<A> : Monad<ForOption> {

    val v: A

    fun exercise() {
    }
}
