/**
 * Write a richer generator for Par<Int> which builds more deeply nested
 * parallel computations than the simple variant we've provided so far.
 */
package chapter8.exercises.ex15

import arrow.core.extensions.list.foldable.foldLeft
import chapter7.sec4.Par
import chapter7.sec4.fork
import chapter7.sec4.unit
import chapter8.Gen
import chapter8.sec4_9.map2
import utils.SOLUTION_HERE

fun pint2(): Gen<Par<Int>> = SOLUTION_HERE()
