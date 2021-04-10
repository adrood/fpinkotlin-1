/**
 * We could also deal with non-strictness using a separate combinotor like
 * we did in chapter 7. Provide a new combinator called defer and make the
 * necessary changes to your existing combinators. What do you think of
 * that approach in this instance?
 *
 * This approach could work, but arguably causes more confusion than what
 * it's worth. For this reason, we will not introduce it and keep our
 * combinators free from lazily initialized parsers.
 */
package chapter9.exercises.ex5

interface Parser<A>

fun <A, B, C> map2(
    pa: Parser<A>,
    pb: Parser<B>,
    f: (A, B) -> C
): Parser<C> = TODO()

infix fun <A> Parser<out A>.or(p: Parser<out A>): Parser<A> = TODO()

fun <A> succeed(a: A): Parser<A> = TODO()

//TODO: implement `defer` here

//TODO: change this method to use `defer`
fun <A> many(pa: Parser<A>): Parser<List<A>> =
    map2(pa, many(pa)) { a, la ->
        listOf(a) + la
    } or succeed(emptyList())
