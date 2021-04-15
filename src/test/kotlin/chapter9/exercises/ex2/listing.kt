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

package chapter9.exercises.ex2

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl

abstract class Listing : ParserDsl<ParseError>()

