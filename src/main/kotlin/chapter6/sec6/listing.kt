package chapter6.sec6

import arrow.core.Id
import arrow.core.Tuple2
import arrow.core.extensions.id.monad.monad
import arrow.mtl.State
import arrow.mtl.extensions.fx

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

//tag::init1[]
// Listing 6.16.
// State declarations and combinators required to perform
// state propagation

// Is a State<RNG, Int> with the ability of generating a single
// random integer
val int: State<RNG, Int> = TODO() // <1>

// returns a State<RNG, List<Int>> that can generate a list of
// random integers
fun ints(x: Int): State<RNG, List<Int>> = TODO() // <2>

// a flatMap function that operates on a State<RNG, A> with a
// function from (A) -> State<RNG, B>
fun <A, B> flatMap(
    s: State<RNG, A>,
    f: (A) -> State<RNG, B>
): State<RNG, B> = TODO() // <3>

// a map function that operates on a s: State<RNG, A> with a
// function that tronsforms A to B
fun <A, B> map(
    s: State<RNG, A>,
    f: (A) -> B
): State<RNG, B> = TODO() // <4>
//end::init1[]

//tag::init2[]
// Listing 6.17.
// State propagation using a series of flatmap and map
val ns: State<RNG, List<Int>> =
    // int will generate  a single random integer
    flatMap(int) { x -> // <1>
        flatMap(int) { y -> // <1>
            // ints(x) generates a list of length x random integers
            map(ints(x)) { xs -> //<2>
                // replaces every element in the list with its
                // remainder when divided by x
                xs.map { it % y } // <3>
            }
        }
    }
//end::init2[]

//tag::init3[]
// Listing 6.18.
// State propagation using a for-comprehension
val ns2: State<RNG, List<Int>> =
    // Open the for-comprehension by passing a code block
    // into State.fx(Id.monad())
    State.fx(Id.monad()) { // <1>
        // int is destructured to an int named x
        val x: Int = int.bind() // <2>
        // int is again destructured to an int named y
        val y: Int = int.bind() // <3>
        // ints(x) is destructured to a List<Int> of length x
        val xs: List<Int> = ints(x).bind() // <4>
        // replace every element in xs with its remainder when
        // divided by y, returns the result
        xs.map { it % y } // <5>
    }
//end::init3[]

//tag::init4[]
// Listing 6.19.
// Combinator to modify the current State
fun <S> modify(f: (S) -> S): State<S, Unit> =
    // Set up the for-comprehension for State
    State.fx(Id.monad()) { // <1>
        // Get the current state and assign it to s
        val s: S = get<S>().bind() // <2>
        // Set the new state of f applied to s
        set(f(s)).bind() // <3>
    }
//end::init4[]

//tag::init5[]
// Listing 6.20.
// The get combinator retrieves, then passes on its state
fun <S> get(): State<S, S> =
    State { s -> Tuple2(s, s) }
//end::init5[]

//tag::init6[]
// Listing 6.21.
// The set combinator updates the state, then returns Unit
fun <S> set(s: S): State<S, Unit> =
    State { Tuple2(s, Unit) }
//end::init6[]
