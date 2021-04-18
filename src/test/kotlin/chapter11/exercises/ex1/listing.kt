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

    fun parMonad(): Monad<ForPar> =

        SOLUTION_HERE()

    fun optionMonad(): Monad<ForOption> =

        SOLUTION_HERE()

    fun listMonad(): Monad<ForList> =

        SOLUTION_HERE()

    fun listKMonad(): Monad<ForListK> =

        SOLUTION_HERE()

    fun sequenceKMonad(): Monad<ForSequenceK> =

        SOLUTION_HERE()
}
//end::init1[]
