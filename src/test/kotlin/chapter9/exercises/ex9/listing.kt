/**
 * At this point, you are going to take over the design process. You'll be
 * creating a Parser<JSON> from scratch using the primitives we've
 * defined. You don't need to worry about the representation of Parser
 * just yet. As you go, you'll undoubtedly discover additional combinators
 * and idioms, notice and factor out common patterns, and so on. Use the
 * skills you've been developing throughout this book, and have fun!
 *
 * Tip: For the tokens of your grmmar, it is a good idea to skip any
 * trailing whitespace to avoid having to deal with whitespace everywhere.
 * Try introducing a combinator for this called token.
 * When sequenceing parsers with product, it is common to want to ignore
 * one of the parsers in the sequence, consider introducing combinators
 * for this purpose called skipL and skipR.
 *
 * Note: This exercise is about defining the algebra consisting of
 * primitive and combinator declarations only. No implementations should
 * appear in the final solution
 */
package chapter9.exercises.ex9

import utils.SOLUTION_HERE

sealed class JSON

object JNull : JSON()
data class JNumber(val get: Double) : JSON()
data class JString(val get: String) : JSON()
data class JBoolean(val get: Boolean) : JSON()
data class JArray(val get: List<JSON>) : JSON()
data class JObject(val get: Map<String, JSON>) : JSON()

object ParseError

interface Parser<A>

//tag::init[]
abstract class Parsers<PE> {

    // primitives

    internal abstract fun string(s: String): Parser<String>

    internal abstract fun regex(r: String): Parser<String>

    internal abstract fun <A> slice(p: Parser<A>): Parser<String>

    internal abstract fun <A> succeed(a: A): Parser<A>

    internal abstract fun <A, B> flatMap(
        p1: Parser<A>,
        f: (A) -> Parser<B>
    ): Parser<B>

    internal abstract fun <A> or(
        p1: Parser<out A>,
        p2: () -> Parser<out A>
    ): Parser<A>

    // other combinators here
}

abstract class ParsersDsl<PE> : Parsers<PE>() {
    // syntactic sugar here
}

abstract class JSONParsers : ParsersDsl<ParseError>() {
    val jsonParser: Parser<JSON> =

        SOLUTION_HERE()
}
//end::init[]
