/**
 * Can you think of any other primitives that might be useful for
 * specifying what error(s) in an or-chain get reported?
 *
 * Tip: Here are two options: we could return the most recent error in the
 * or chain, or we could return whichever error occurred after getting
 * furthest into the input string
 */
package chapter9.solutions.ex10

import chapter9.solutions.final.Parser

interface Parsers {
    //tag::init1[]
    fun <A> furthest(pa: Parser<A>): Parser<A>
    //end::init1[]

    //tag::init2[]
    fun <A> latest(pa: Parser<A>): Parser<A>
    //end::init2[]
}
