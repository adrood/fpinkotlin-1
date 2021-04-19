package chapter10.sec4

import chapter10.sec1.stringMonoid

//tag::init1[]
// Listing 10.2
// ADT representation of partial results of a word count
sealed class WC

// A Stub is an accumulation of characters that form a partial word
data class Stub(val chars: String) : WC() //<1>
// A Part contains a left stub, a word count, and a right stub
data class Part(val ls: String, val words: Int, val rs: String) : WC()//<2>
//end::init1[]

val sidebar = {
    //tag::init2[]
    "foo".length + "bar".length == ("foo" + "bar").length
    //end::init2[]

    val M = stringMonoid
    val N = stringMonoid
    val f = { a: String -> a }
    val x = ""
    val y = ""

    //tag::init3[]
    M.combine(f(x), f(y)) == f(N.combine(x, y))
    //end::init3[]
}

// Exercises 10.10, 10.11