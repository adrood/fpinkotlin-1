package chapter11.sec5_2

import arrow.core.extensions.list.foldable.foldLeft
import arrow.higherkind
import chapter11.sec2.Monad

@higherkind
data class State<S, out A>(val run: (S) -> Pair<A, S>) : StateOf<S, A> {

    companion object {
        fun <S, A> unit(a: A): State<S, A> =
            State { s: S -> a to s }
    }

    fun <B> map(f: (A) -> B): State<S, B> =
        flatMap { a: A -> unit<S, B>(f(a)) }

    fun <B> flatMap(f: (A) -> State<S, B>): State<S, B> =
        State { s1: S ->
            val (a: A, s2: S) = this.run(s1)
            f(a).run(s2)
        }

    //tag::init5[]
    fun <S> getState(): State<S, S> = State { s -> s to s }

    fun <S> setState(s: S): State<S, Unit> = State { Unit to s }
    //end::init5[]
}

//tag::init2[]

// Listing 11.17.
// The state monad interface with hardcoded partially applied types

// The Monad type constructor takes partially applied type parameter
// StatePartialOf for any S
interface StateMonad<S> : Monad<StatePartialOf<S>> { // <1>

    // Monadic combinators no longer restricted to deal in
    // single type parameter currency
    override fun <A> unit(a: A): StateOf<S, A> // <2>

    override fun <A, B> flatMap(
        fa: StateOf<S, A>,
        f: (A) -> StateOf<S, B>
    ): StateOf<S, B>
}
//end::init2[]

//tag::init4[]

// Listing 11.18.
// A partially applied State monad that brings flexibility to the
// type family members it represents

val intStateMonad: StateMonad<Int> = object : StateMonad<Int> {
    override fun <A> unit(a: A): StateOf<Int, A> =
        State { s -> a to s }

    override fun <A, B> flatMap(
        fa: StateOf<Int, A>,
        f: (A) -> StateOf<Int, B>
    ): StateOf<Int, B> =
        fa.fix().flatMap { a -> f(a).fix() }
}
//end::init4[]

//tag::init6[]

// Listing 11.19.
// Getting and setting state with sequence of flatmap and map operations

val F = intStateMonad

/**
 * This function numbers all the elements in a list using a State action.
 * It keeps a state that's an Int, which is incremented at each step.
 */
fun <A> zipWithIndex(la: List<A>): List<Pair<Int, A>> =
    la.foldLeft(F.unit(emptyList<Pair<Int, A>>())) { acc, a ->
        acc.fix().flatMap { xs ->
            acc.fix().getState<Int>().flatMap { n ->
                acc.fix().setState(n + 1).map { _ ->
                    listOf(n to a) + xs
                }
            }
        }
    }.fix().run(0).first.reversed()
//end::init6[]

/*
//tag::init7[]

// Lisiting 11.20.
// Getting and setting state with a for-comprehenseion
...
{ acc: StateOf<Int, List<Pair<Int, A>>>, a: A ->
    acc.fx {
        val xs = acc.bind()
        val n = acc.getState().bind()
        acc.setState(n + 1).bind()
        listOf(n to a) + xs
    }
}
...
//end::init7[]
 */

fun main() {
    println(zipWithIndex(listOf(1, 2, 3, 4, 5)))
}

// Exercises 11.16 - 11.18