/**
 * Now that we have State monad, try out to see how it behaves. Declare
 * some values of replicateM, map2 and sequence with type declarations
 * using the intMonad above. Describe how each behaves under the covers?
 */
package chapter11.solutions.ex17

import chapter10.List
import chapter11.State
import chapter11.StateMonad
import chapter11.StateOf
import chapter11.fix

val intMonad: StateMonad<Int> = object : StateMonad<Int> {
    override fun <A> unit(a: A): StateOf<Int, A> =
        State { s -> a to s }

    override fun <A, B> flatMap(
        fa: StateOf<Int, A>,
        f: (A) -> StateOf<Int, B>
    ): StateOf<Int, B> =
        fa.fix().flatMap { a -> f(a).fix() }
}

fun main() {

    val stateA: State<Int, Int> = State { a: Int -> a to (10 + a) }
    val stateB: State<Int, Int> = State { b: Int -> b to (10 * b) }

    //tag::init[]
    /**
     * replicateM for State repeats the same state transition a number
     * of times and returns a list of the results. It's not passing the
     * same starting state many times, but chaining the calls together
     * so that the output state of one is the input state of the other
     */
    fun replicateIntState(): StateOf<Int, List<Int>> =
        intMonad.replicateM(5, stateA)

    /**
     * map2 works similarly in that it takes two state transitions and
     * feeds the output of one to the input of the other. The outputs
     * are not put in a list, but combined with a function f.
     */
    fun map2IntState(): StateOf<Int, Int> =
        intMonad.map2(stateA, stateB) { a, b -> a * b }

    /**
     * sequence takes an entire list of state transitions and does the
     * same thing as replicateM: it feeds the output state of the first
     * state transition to the input state of the next, and so on. The
     * results are accumulated in a list.
     */
    fun sequenceIntState(): StateOf<Int, List<Int>> =
        intMonad.sequence(List.of(stateA, stateB))
    //end::init[]
}
