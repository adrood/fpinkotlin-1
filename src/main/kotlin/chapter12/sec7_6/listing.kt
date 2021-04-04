package chapter12.sec7_6

import arrow.Kind
import chapter10.List
import chapter10.None
import chapter10.Option
import chapter10.Some
import chapter10.fix
import chapter11.Monad
import chapter11.listMonad
import chapter12.assertEqual

//tag::init1[]

// Listing 12.22.
// A monad transformer is used to compose Option with any other monad

data class OptionT<M, A>(
    // Option<A> is nested inside kind M
    val value: Kind<M, Option<A>>, // <1>
    // Monad instance MM allows us to work with type M
    val MM: Monad<M> // <2>
) {
    // The flatMap method conveniently mimics that of Option, while
    // using Monad<M> to operate on M
    fun <B> flatMap(f: (A) -> OptionT<M, B>): OptionT<M, B> = // <3>
        OptionT(MM.flatMap(value) { oa: Option<A> ->
            when (oa) {
                is None -> MM.unit(None)
                is Some -> f(oa.get).value
            }
        }, MM)
}
//end::init1[]

fun main() {
    //tag::init2[]
    val F = listMonad
    // Declare ls, a List<Option<Int>>
    val ls = List.of(Some(1), None, Some(2)) // <1>
    val xs: List<Option<String>> =
        // Use the monad transformer OptionT to operate
        // directly on the nested i:Int
        OptionT(ls, F).flatMap { i: Int -> // <2>
            // Emit the OptionT instance for each element as
            // required by flatMap
            OptionT(F.unit(Some("${i * 2}")), F) // <3>
        }.value.fix()

    assertEqual(xs, List.of(Some("2"), None, Some("4")))
    //end::init2[]
}
