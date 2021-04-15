/**
 * Try coming up with laws to specify the behavior of product.
 *
 * Tip: Multiplication of numbers is always associative, so
 *
 *     (a * b) * c = a * (b * c).
 *
 * Is this property analogous to parsers? What is there to say about the
 * relationship between map and product?
 *
 * The product combinator is associative, so both expressions are more
 * or less equal. The only difference here is how the pairs are nested.
 * The
 *
 *     (a product b) product c
 *
 * parser returns a
 *
 *     Pair<Pair<A, B>, C>
 *
 * while the
 *
 *     a product (b product c)
 *
 * combinator returns a
 *
 *     Pair<A, Pair<B, C>>
 *
 * We can easily introduce some new functions called unbiasL and
 * unbiasR to flatten these structures out into Triples.
 *
 * This now allows us to express the law of associativity as follows:
 *
 *     ((a product b) product c).map(::unbiasL) ==
 *         (a product (b product c)).map(::unbiasR)
 *
 *  Another interesting obeservation is the relationship between map and
 *  product. It is possible to map either before or after taking the
 *  product of two parsers without affecting the behavior.
 *  For instance, if a and b were both parser<String>, and f and g both
 *  computed the length of a string, it doesn't matter if we map over the
 *  results of a and b to compute their respective lengths before, or
 *  whether we it after applying the product.
 */
package chapter9.solutions.ex2

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl

abstract class Listing : ParserDsl<ParseError>() {

    private infix fun <A, B> Parser<A>.product(
        pb: Parser<B>
    ): Parser<Pair<A, B>> = TODO()

    val a = succeed("a")
    val b = succeed("b")
    val c = succeed("c")

    val listing1 = {
        //tag::init1[]
        (a product b) product c
        a product (b product c)
        //end::init1[]
    }

    //tag::init2[]
    fun <A, B, C> unbiasL(p: Pair<Pair<A, B>, C>): Triple<A, B, C> =
        Triple(p.first.first, p.first.second, p.second)

    fun <A, B, C> unbiasR(p: Pair<A, Pair<B, C>>): Triple<A, B, C> =
        Triple(p.first, p.second.first, p.second.second)
    //end::init2[]

    val listing2 = {
        //tag::init3[]
        ((a product b) product c).map(::unbiasL) ==
            (a product (b product c)).map(::unbiasR)
        //end::init3[]
    }

    fun f(s: String) = s.length
    fun g(s: String) = s.length

    val listing3 = {
        //tag::init4[]
        a.map(::f) product b.map(::g) ==
            (a product b).map { (a1, b1) -> f(a1) to g(b1) }
        //end::init4[]
    }
}
