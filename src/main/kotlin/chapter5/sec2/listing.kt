package chapter5.sec2

import chapter4.None
import chapter4.Option
import chapter4.Some

//tag::init1[]
/**
 * Listing 5.2.
 * Definition for the Stream data type with its sealed implementations.
 */
sealed class Stream<out A>

data class Cons<out A>(
    val head: () -> A,
    val tail: () -> Stream<A>
) : Stream<A>()

object Empty : Stream<Nothing>()
//end::init1[]

//tag::init2[]
fun <A> Stream<A>.headOption(): Option<A> =
    when (this) {
        is Empty -> None
        // Explicit forcing of the head thunk using 'head()'
        is Cons -> Some(head()) // <1>
    }
//end::init2[]

// 5.2.1 Memoizing of streams and avoid recomputation

val tl: Stream<String> = TODO()
fun expensive(c: String): String = TODO()
val y: String = TODO()

//tag::init3[]
val x = Cons({ expensive(y) }, { tl })
val h1 = x.headOption()
val h2 = x.headOption()
//end::init3[]

//tag::init4[]
// smart constructor
fun <A> cons(hd: () -> A, tl: () -> Stream<A>): Stream<A> {
    val head: A by lazy(hd)
    val tail: Stream<A> by lazy(tl)
    return Cons({ head }, { tail })
}
//end::init4[]

//tag::init5[]
// smart constructor
fun <A> empty(): Stream<A> = Empty

// smart constructors used
fun <A> of(vararg xs: A): Stream<A> =
    if (xs.isEmpty()) empty()
    else cons({ xs[0] },
        { of(*xs.sliceArray(1 until xs.size)) })
//end::init5[]

// Exercises 5.1 - 5.3