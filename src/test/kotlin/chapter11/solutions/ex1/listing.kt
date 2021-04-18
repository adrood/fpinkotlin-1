/**
 * Write monad instances for Par, Option and List. Additionally, provide
 * monad instances for arrow.core.ListK and arrow.core.SequenceK
 *
 * Note: The ListK and SequenceK types provided by Arrow are wrapper
 * classes that turn ther platform equivalents, List and Sequence,
 * into fully equipped type constructors.
 *
 * Tip: The unit and flatMap combinators have already been implemented
 * in various ways for these types. Simply call them from your Monad
 * implementation.
 */

package chapter11.solutions.ex1

import arrow.Kind
import arrow.core.ForListK
import arrow.core.ForSequenceK
import arrow.core.ListK
import arrow.core.ListKOf
import arrow.core.SequenceK
import arrow.core.fix
import chapter10.ForList
import chapter10.ForOption
import chapter10.List
import chapter10.ListOf
import chapter10.OptionOf
import chapter10.Some
import chapter10.fix
import chapter11.ForPar
import chapter11.Par
import chapter11.ParOf
import chapter11.fix
import chapter11.sec2.Monad

//tag::init1[]
object Monads {

    fun parMonad() = object : Monad<ForPar> {

        override fun <A> unit(a: A): ParOf<A> = Par.unit(a)

        override fun <A, B> flatMap(
            fa: ParOf<A>,
            f: (A) -> ParOf<B>
        ): ParOf<B> =
            fa.fix().flatMap { f(it).fix() }
    }

    fun optionMonad() = object : Monad<ForOption> {

        override fun <A> unit(a: A): OptionOf<A> = Some(a)

        override fun <A, B> flatMap(
            fa: OptionOf<A>,
            f: (A) -> OptionOf<B>
        ): OptionOf<B> =
            fa.fix().flatMap { f(it).fix() }
    }

    fun listMonad() = object : Monad<ForList> {

        override fun <A> unit(a: A): ListOf<A> = List.of(a)

        override fun <A, B> flatMap(
            fa: ListOf<A>,
            f: (A) -> ListOf<B>
        ): ListOf<B> =
            fa.fix().flatMap { f(it).fix() }
    }

    fun listKMonad() = object : Monad<ForListK> {

        override fun <A> unit(a: A): ListKOf<A> = ListK.just(a)

        override fun <A, B> flatMap(
            fa: ListKOf<A>,
            f: (A) -> ListKOf<B>
        ): ListKOf<B> =
            fa.fix().flatMap(f)
    }

    fun sequenceKMonad() = object : Monad<ForSequenceK> {

        override fun <A> unit(a: A): Kind<ForSequenceK, A> =
            SequenceK.just(a)

        override fun <A, B> flatMap(
            fa: Kind<ForSequenceK, A>,
            f: (A) -> Kind<ForSequenceK, B>
        ): Kind<ForSequenceK, B> {
            return fa.fix().flatMap(f)
        }
    }
}
//end::init1[]
