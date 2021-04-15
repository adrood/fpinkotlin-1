package chapter11.exercises.ex19

import arrow.Kind
import arrow.Kind2
import arrow.core.extensions.list.foldable.foldLeft
import chapter11.sec2.Monad
import utils.SOLUTION_HERE

fun <A> prefixWith(la: List<A>, prefix: String): List<String> =
    SOLUTION_HERE()

fun main() {
    println(prefixWith(listOf(1, 2, 3, 4, 5), "before"))
}
