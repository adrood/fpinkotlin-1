/**
 * To cement your understanding of monads, give a monad instance for the
 * Reader data type and explain what it means. Also, take some time to
 * answer the following questions:
 * - What are its primitive operations?
 * - What is the action of flatMap?
 * - What meaning does it give to monadic functions like sequence,
 *   join, and replicateM?
 * - What meaning does it give to the monadic laws?
 *
 * Tip: This monad is very similar to the State monad, except that it is
 * "read-only". You can "ask" from it, but not "set" the R value that
 * flatMap carries along.
 */
package chapter11.solutions.ex19

import arrow.Kind
import arrow.Kind2
import arrow.core.extensions.list.foldable.foldLeft
import chapter11.sec2.Monad

//tag::init[]
sealed class ForReader private constructor() {
    companion object
}

typealias ReaderOf<R, A> = Kind2<ForReader, R, A>

typealias ReaderPartialOf<R> = Kind<ForReader, R>

fun <R, A> ReaderOf<R, A>.fix() = this as Reader<R, A>

interface ReaderMonad<R> : Monad<ReaderPartialOf<R>>

data class Reader<R, A>(val run: (R) -> A) : ReaderOf<R, A> {

    companion object {
        fun <R, A> unit(a: A): Reader<R, A> = Reader { a }
    }

    fun <B> map(f: (A) -> B): Reader<R, B> =
        this.flatMap { a: A -> unit<R, B>(f(a)) }

    /**
     * The action of flatMap here is to pass the r argument along to both
     * the outer Reader and also to the result of f, the inner Reader.
     * Similar to how State passes along a state, except that in Reader
     * the "state" is read-only.
     */
    fun <B> flatMap(f: (A) -> Reader<R, B>): Reader<R, B> =
        Reader { r: R -> f(run(r)).run(r) }

    fun <A> ask(): Reader<R, R> = Reader { r -> r }
}

fun <R> readerMonad() = object : ReaderMonad<R> {
    override fun <A> unit(a: A): ReaderOf<R, A> =
        Reader { a }

    override fun <A, B> flatMap(
        fa: ReaderOf<R, A>,
        f: (A) -> ReaderOf<R, B>
    ): ReaderOf<R, B> =
        fa.fix().flatMap { a -> f(a).fix() }
}
//end::init[]

val F: ReaderMonad<String> = readerMonad()

fun <A> prefixWith(la: List<A>, prefix: String): List<String> =
    la.foldLeft(F.unit(emptyList<String>())) { acc, a ->
        acc.fix().flatMap { xs ->
            acc.fix().ask<String>().map { p ->
                listOf("$p-$a") + xs
            }
        }
    }.fix().run(prefix).reversed()

fun main() {
    println(prefixWith(listOf(1, 2, 3, 4, 5), "before"))
}
