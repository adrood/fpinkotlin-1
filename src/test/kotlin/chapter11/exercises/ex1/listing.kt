/**
 * Write monad instances for Par, Option and List. Additionally, provide
 * monad instances for arrow.core.ListK and arrow.core.SequenceK
 *
 * Note: The ListK and SequenceK types provided by Arrow are wrapper
 * classes that turn their platform equivalents, List and Sequence,
 * into fully equipped type constructors.
 *
 * Tip: The unit and flatMap combinators have already been implemented
 * in various ways for these types. Simply call them from your Monad
 * implementation.
 */
package chapter11.exercises.ex1

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
import utils.SOLUTION_HERE

//tag::init1[]
object Monads {

    fun parMonad(): Monad<ForPar> = object : Monad<ForPar> {
        override fun <A> unit(a: A): Kind<ForPar, A> {
            return Par.unit(a)
        }

        override fun <A, B> flatMap(
            fa: Kind<ForPar, A>,
            f: (A) -> Kind<ForPar, B>
        ): Kind<ForPar, B> {
            return fa.fix().flatMap { f(it).fix() }
        }
    }

    fun optionMonad(): Monad<ForOption> = object : Monad<ForOption> {
        override fun <A> unit(a: A): Kind<ForOption, A> {
            TODO("Not yet implemented")
        }

        override fun <A, B> flatMap(
            fa: Kind<ForOption, A>,
            f: (A) -> Kind<ForOption, B>
        ): Kind<ForOption, B> {
            TODO("Not yet implemented")
        }
    }

    fun listMonad(): Monad<ForList> = object : Monad<ForList> {
        override fun <A> unit(a: A): Kind<ForList, A> {
            TODO("Not yet implemented")
        }

        override fun <A, B> flatMap(
            fa: Kind<ForList, A>,
            f: (A) -> Kind<ForList, B>
        ): Kind<ForList, B> {
            TODO("Not yet implemented")
        }
    }

    fun listKMonad(): Monad<ForListK> = object : Monad<ForListK> {
        override fun <A> unit(a: A): Kind<ForListK, A> {
            return ListK.just(a)
        }

        override fun <A, B> flatMap(
            fa: Kind<ForListK, A>,
            f: (A) -> Kind<ForListK, B>
        ): Kind<ForListK, B> {
            TODO("Not yet implemented")
        }
    }

    fun sequenceKMonad(): Monad<ForSequenceK> = object : Monad<ForSequenceK> {
        override fun <A> unit(a: A): Kind<ForSequenceK, A> {
            TODO("Not yet implemented")
        }

        override fun <A, B> flatMap(
            fa: Kind<ForSequenceK, A>,
            f: (A) -> Kind<ForSequenceK, B>
        ): Kind<ForSequenceK, B> {
            TODO("Not yet implemented")
        }
    }
}
//end::init1[]
