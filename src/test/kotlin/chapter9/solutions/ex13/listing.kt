/**
 *
 * Implement run, as well as any of the remaining primitives
 * not yet implmented using our current representation of Parser.
 * Try running your JSON parser on various inputs
 */
package chapter9.solutions.ex13

import chapter9.solutions.final.Location
import chapter9.solutions.final.Parser
import chapter9.solutions.final.Parsers
import chapter9.solutions.final.Result

abstract class ParsersImpl<PE> : Parsers<PE>() {
    //tag::init[]
    override fun <A> run(p: Parser<A>, input: String): Result<A> =
        p(Location(input))
    //end::init[]
}