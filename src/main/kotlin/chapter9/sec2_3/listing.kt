package chapter9.sec2_3

import chapter9.sec2_2.ParseError
import chapter9.sec2_2.Parser
import chapter9.sec2_2.Parsers

abstract class Listing : Parsers<ParseError> {

    fun <A, B, C> map2(
        pa: Parser<A>,
        pb: Parser<B>,
        f: (A, B) -> C
    ): Parser<C> = TODO()

    val listing = {

        infix fun <A> Parser<A>.or(that: Parser<A>): Parser<A> =
            this.or(that)

        //tag::init1[]
        // A neat little extension method that allows for creating
        // a list by prefixing an element to a list of elements.
        infix fun <T> T.cons(la: List<T>): List<T> = listOf(this) + la

        // We are calling many recursively in the second argument to
        // map2, which is a strict evaluation of its second argument.
        fun <A> many(pa: Parser<A>): Parser<List<A>> =
            map2(pa, many(pa)) { a, la ->
                a cons la
            } or succeed(emptyList())
        //end::init1[]

        val p = char('a')
        //tag::init2[]
        // A simplified program trace
        // This indicates that we need to make product and map2 non-strict
        // in their second arguments (see listing in sec_2_4)
        many(p)
        map2(p, many(p)) { a, la -> a cons la }
        map2(p, map2(p, many(p)) { a, la -> a cons la }) { a, la ->
            a cons la
        }
        //end::init2[]
    }
}
