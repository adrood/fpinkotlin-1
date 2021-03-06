/**
 * Before continuing, see if you can define many in terms of or,
 * map2, and succeed
 */
package chapter9.exercises.ex3

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {

    fun <A, B, C> map2(
        pa: Parser<A>,
        pb: Parser<B>,
        f: (A, B) -> C
    ): Parser<C> = TODO()

    init {
        //tag::init1[]
        fun <A> many(pa: Parser<A>): Parser<List<A>> =

            SOLUTION_HERE()
        //end::init1[]
    }
}
