package chapter9.sec1_2

import arrow.core.Either
import arrow.core.Right

//tag::init1[]
interface Parsers<PE> {

    interface Parser<A>

    // The string parser for turning String into Parser<String>
    fun string(s: String): Parser<String> // <1>

    // The or combinator for deciding between two instances
    // of Parser<A>
    fun <A> or(a1: Parser<A>, a2: Parser<A>): Parser<A> // <2>

    // Infix extension method to make the 'or' combinator more
    // pleasing to use on Strings
    infix fun String.or(other: String): Parser<String> =
        or(string(this), string(other)) // <3>

    fun <A> run(p: Parser<A>, input: String): Either<PE, A>
}
//end::init1[]

object ParseError

abstract class Example : Parsers<ParseError> {
    init {
        //tag::init2[]
        run("abra" or "cadabra", "abra") == Right("abra")
        //end::init2[]
    }
}
