package chapter5.sec3

import chapter3.List
import chapter4.Option

//tag::imports[]
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

//end::imports[]

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> = TODO()

fun <A> Stream<A>.filter(f: (A) -> Boolean): Stream<A> = TODO()

fun <A> Stream<A>.toList(): List<A> = TODO()

fun <A> Stream<A>.headOption(): Option<A> = TODO()

sealed class Stream<out A> {

    companion object {
        fun <A> cons(hd: () -> A, tl: () -> Stream<A>): Stream<A> {
            val head: A by lazy(hd)
            val tail: Stream<A> by lazy(tl)
            return Cons({ head }, { tail })
        }

        fun <A> of(vararg xs: A): Stream<A> =
            if (xs.isEmpty()) empty()
            else cons({ xs[0] },
                { of(*xs.sliceArray(1 until xs.size)) })

        fun <A> empty(): Stream<A> = Empty
    }

    //tag::init1[]
    fun exists(p: (A) -> Boolean): Boolean =
        when (this) {
            is Cons -> p(this.head()) || this.tail().exists(p)
            else -> false
        }
    //end::init1[]

    //tag::init2[]
    /**
     * Listing 5.3.
     * The foldRight function on Stream is used to generalize recursion.
     */
    fun <B> foldRight(
        // The type '() -> B' means that the function f takes the
        // second parameter by name and may choose not to evaluate it
        z: () -> B,
        f: (A, () -> B) -> B // <1>
    ): B =
        when (this) {
            // If f doesn't evaluate its second argument, the
            // recursion never occurs
            is Cons -> f(this.head()) {
                tail().foldRight(z, f) // <2>
            }
            is Empty -> z()
        }
    //end::init2[]

    //tag::init3[]
    fun exists2(p: (A) -> Boolean): Boolean =
        foldRight({ false }, { a, b -> p(a) || b() })
    //end::init3[]

    //tag::init4[]
    fun find(p: (A) -> Boolean): Option<A> =
        filter(p).headOption()
    //end::init4[]
}

data class Cons<out A>(
    val head: () -> A,
    val tail: () -> Stream<A>
) : Stream<A>()

object Empty : Stream<Nothing>()

val trace = {
    //tag::init5[]
    Stream.of(1, 2, 3, 4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList()

    // Apply map to the first element

    Stream.cons({ 11 }, { Stream.of(2, 3, 4).map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // <1>

    // Apply filter to the first element;
    // predicate returns false

    Stream.of(2, 3, 4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // <2>

    // Apply map to the second element

    Stream.cons({ 12 }, { Stream.of(3, 4).map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // <3>

    // Apply filter to the second element;
    // predicate returns true;
    // apply second map;
    // produces first element of result

    ConsL(36, Stream.of(3, 4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList()) // <4>

    // Apply map to the third element

    ConsL(36, Stream.cons({ 13 }, { Stream.of(4).map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // <5>
    )

    // Apply filter to the third element;
    // predicate returns false

    ConsL(36, Stream.of(4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList()) // <6>

    // Apply map to the last element

    ConsL(36, Stream.cons({ 14 }, { Stream.empty<Int>().map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // <7>
    )

    // Apply filter to the last element;
    // predicate returns true;
    // apply second map;
    // produces second element of result

    ConsL(36, ConsL(42, Stream.empty<Int>().map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList())) // <8>

    // End of Stream Empty has been reached.
    // Now map and filter have no more work to do;
    // Empty stream becomes NilL

    ConsL(36, ConsL(42, NilL)) // <9>
    //end::init5[]
}
// Exercises 5.4 -5.7