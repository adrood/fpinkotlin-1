/**
 * Section 9.2.2. Slicing and nonempty repetition
 *
 * Using product, implement the now-familiar combinator map2.
 * In turn, use this to implement many1 in terms of many.
 *
 * Tip: Consider mapping over the result of product
 */
package chapter9.exercises.ex1

import chapter9.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {

    //tag::init1[]
    override fun <A, B, C> map2(
        pa: Parser<A>,
        pb: () -> Parser<B>,
        f: (A, B) -> C
    ): Parser<C> =

        SOLUTION_HERE()
    //end::init1[]

    //tag::init2[]
    override fun <A> many1(p: Parser<A>): Parser<List<A>> =

        SOLUTION_HERE()
    //end::init2[]
}
