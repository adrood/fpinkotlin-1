/**
 * Write the purely functional versions of partition and gs.
 *
 * Tip: Think of this in terms of the quicksort function of Listing 14.1,
 * written as procedural code with a for comprehension. The boilerplate
 * for writing the for comprehension using Arrow fx has already been
 * provided.
 */
package chapter14.exercises.ex2

import chapter14.sec2.ST
import chapter14.sec2.STArray
import chapter14.sec2.STRef
import chapter14.sec2.fx
import utils.SOLUTION_HERE

//import chapter14.sec2.fx

fun <S> partition1(
    arr: STArray<S, Int>,
    l: Int,
    r: Int,
    pivot: Int
): ST<S, Int> =
    arr.read(pivot).flatMap { vp ->
        arr.swap(pivot, r).flatMap {
            STRef<S, Int>(l).flatMap { j ->
                (l until r).fold(noop<S>()) { st: ST<S, Unit>, i: Int ->
                    st.flatMap {
                        arr.read(i).flatMap { vi ->
                            if (vi < vp) {
                                j.read().flatMap { vj ->
                                    arr.swap(i, vj).flatMap {
                                        j.write(vj + 1)
                                    }
                                }
                            } else noop()
                        }
                    }
                }.flatMap {
                    j.read().flatMap { x ->
                        arr.swap(x, r).map { x }
                    }
                }
            }
        }
    }

//tag::init[]
fun <S> partition(
    arr: STArray<S, Int>,
    l: Int,
    r: Int,
    pivot: Int
): ST<S, Int> =

    SOLUTION_HERE()

fun <S> qs(arr: STArray<S, Int>, l: Int, r: Int): ST<S, Unit> =

    SOLUTION_HERE()

fun <S> noop() = ST<S, Unit> { Unit }
//end::init[]
