package chapter12.sec7_2

import arrow.Kind
import chapter10.Foldable
import chapter11.Monad
import chapter11.State
import chapter11.StateOf
import chapter11.StatePartialOf
import chapter11.fix
import chapter12.Cons
import chapter12.Functor
import chapter12.List
import chapter12.Nil

interface Applicative<F> : Functor<F> {
    fun <A> unit(a: A): Kind<F, A>
    fun <A, B, C> map2(
        fa: Kind<F, A>,
        fb: Kind<F, B>,
        f: (A, B) -> C
    ): Kind<F, C>
}

//tag::init1[]

// Listing 12.16.
// A monad for a partially applied state

typealias StateMonad<S> = Monad<StatePartialOf<S>>

fun <S> stateMonad() = object : StateMonad<S> {

    override fun <A> unit(a: A): StateOf<S, A> =
        State { s -> a to s }

    override fun <A, B> flatMap(
        fa: StateOf<S, A>,
        f: (A) -> StateOf<S, B>
    ): StateOf<S, B> =
        fa.fix().flatMap { f(it).fix() }

    override fun <A, B, C> compose(
        f: (A) -> StateOf<S, B>,
        g: (B) -> StateOf<S, C>
    ): (A) -> StateOf<S, C> =
        { a -> join(map(f(a), g)) }
}
//end::init1[]

//tag::init2[]

// Listing 12.17.
// An applicative that cloaks the state monad for use in a traverse
// function.

fun <S> stateMonadApplicative(m: StateMonad<S>) =
    object : Applicative<StatePartialOf<S>> {

        override fun <A> unit(a: A): Kind<StatePartialOf<S>, A> =
            // Delegate the applicative unit call to monad m
            m.unit(a) // <1>

        override fun <A, B, C> map2(
            fa: Kind<StatePartialOf<S>, A>,
            fb: Kind<StatePartialOf<S>, B>,
            f: (A, B) -> C
        ): Kind<StatePartialOf<S>, C> =
            // Delegate the applicative map2 call to monad m
            m.map2(fa, fb, f) // <2>

        override fun <A, B> map(
            fa: Kind<StatePartialOf<S>, A>,
            f: (A) -> B
        ): Kind<StatePartialOf<S>, B> =
            // Delegate the functor map call to monad m
            m.map(fa, f) // <3>
    }
//end::init2[]

interface Traversable<F> : Functor<F>, Foldable<F> {

    fun <G, A, B> traverse(
        fa: Kind<F, A>,
        AP: Applicative<G>,
        f: (A) -> Kind<G, B>
    ): Kind<G, Kind<F, B>>

    //tag::init3[]
    fun <S, A, B> traverseS(
        fa: Kind<F, A>,
        f: (A) -> State<S, B>
    ): State<S, Kind<F, B>> =
        traverse(
            fa,
            stateMonadApplicative(stateMonad<S>())
        ) { a -> f(a).fix() }.fix()
    //end::init3[]

    //tag::init4[]

    // Listing 12.18.
    // Zip a list with its index using a state action

    fun <A> zipWithIndex(ta: Kind<F, A>): Kind<F, Pair<A, Int>> =
        traverseS(ta) { a: A ->
            // Get the current state, a counter
            State.get<Int>().flatMap { s: Int -> // <1>
                // Set the current state as the incremented counter value
                State.set(s + 1).map { _ -> // <2>
                    a to s
                }
            }
            // Run the state action starting with index 0
        }.run(0).first // <3>
    //end::init4[]

    //tag::init5[]

    // Listing 12.19.
    // Convert any traversable to a list using a state action

    fun <A> toList(ta: Kind<F, A>): List<A> =
        traverseS(ta) { a: A ->
            // Get the current state, an accumulated list
            State.get<List<A>>().flatMap { la -> // <1>
                // Add the current element as the new head of the
                // Cons and set as new state
                State.set<List<A>>(Cons(a, la)).map { _ -> // <2>
                    Unit
                }
            }
            // Run the state action starting with Nil, then
            // reverse the list
        }.run(Nil).second.reverse() // <3>
    //end::init5[]
}

interface Traversable2<F> : Functor<F>, Foldable<F> {

    fun <S, A, B> traverseS(
        fa: Kind<F, A>,
        f: (A) -> State<S, B>
    ): State<S, Kind<F, B>> = TODO()

    //tag::init6[]

    // Listing 12.20.
    // Generalise a state traversal in the mapAccum function

    fun <S, A, B> mapAccum(
        fa: Kind<F, A>,
        s: S,
        f: (A, S) -> Pair<B, S>
    ): Pair<Kind<F, B>, S> =
        traverseS(fa) { a: A ->
            State.get<S>().flatMap { s1 ->
                val (b, s2) = f(a, s1)
                State.set(s2).map { _ -> b }
            }
        }.run(s)

    fun <A> zipWithIndex(ta: Kind<F, A>): Kind<F, Pair<A, Int>> =
        mapAccum(ta, 0) { a, s ->
            (a to s) to (s + 1)
        }.first

    fun <A> toList(ta: Kind<F, A>): List<A> =
        mapAccum(ta, Nil) { a: A, s: List<A> ->
            Unit to Cons(a, s)
        }.second.reverse()
    //end::init6[]
}
