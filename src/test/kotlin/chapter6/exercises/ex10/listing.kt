/**
 * Generalize the functions unit, map, map2, flatMap, and sequence. Add
 * them as methods on the State data class where possible. Otherwise you
 * should put them in the State companion object.
 *
 * Tip: Use the specialized functions for Rand as inspiration.
 * Recall that if you have a
 *
 *     f: (S) -> Pair(A, S),
 *
 * you can create a State<S, A> just by writing State(f). The function f
 * can also be declared inline with a lambda:
 *
 *     State {s: S ->
 *     ...
 *         Pair(a, s2)
 *     }
 */
package chapter6.exercises.ex10

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import chapter6.RNG
import chapter6.rng1
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//tag::init[]
data class State<S, out A>(val run: (S) -> Pair<A, S>) {

    companion object {
        fun <S, A> unit(a: A): State<S, A> =

            SOLUTION_HERE()

        fun <S, A, B, C> map2(
            ra: State<S, A>,
            rb: State<S, B>,
            f: (A, B) -> C
        ): State<S, C> =

            SOLUTION_HERE()

        fun <S, A> sequence(fs: List<State<S, A>>):
            State<S, List<A>> =

            SOLUTION_HERE()
    }

    fun <B> map(f: (A) -> B): State<S, B> =

        SOLUTION_HERE()

    fun <B> flatMap(f: (A) -> State<S, B>): State<S, B> =

        SOLUTION_HERE()
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise10 : WordSpec({
    "unit" should {
        "!compose a new state of pure a" {
            State.unit<RNG, Int>(1).run(rng1) shouldBe (1 to rng1)
        }
    }
    "map" should {
        "!transform a state" {
            State.unit<RNG, Int>(1)
                .map { it.toString() }
                .run(rng1) shouldBe ("1" to rng1)
        }
    }
    "flatMap" should {
        "!transform a state" {
            State.unit<RNG, Int>(1)
                .flatMap { i ->
                    State.unit<RNG, String>(i.toString())
                }.run(rng1) shouldBe ("1" to rng1)
        }
    }
    "map2" should {
        "!combine the results of two actions" {

            val combined: State<RNG, String> =
                State.map2(
                    State.unit(1.0),
                    State.unit(1)
                ) { d: Double, i: Int ->
                    ">>> $d double; $i int"
                }

            combined.run(rng1).first shouldBe ">>> 1.0 double; 1 int"
        }
    }
    "sequence" should {
        "!combine the results of many actions" {

            val combined: State<RNG, List<Int>> =
                State.sequence(
                    List.of(
                        State.unit(1),
                        State.unit(2),
                        State.unit(3),
                        State.unit(4)
                    )
                )

            combined.run(rng1).first shouldBe List.of(1, 2, 3, 4)
        }
    }
})
