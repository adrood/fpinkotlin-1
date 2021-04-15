/**
 * Add a combinator on STArray to fill the contained array using a Map.
 * Each key in the map represents an array index, and the corresponding
 * value should be written at that index position. For example,
 *
 *     sta.fill(mapOf(0 to "a", 2 to "b"
 *
 * should write the value "a" at index 0 and "b" at index 2 in the
 * underlying array. Use existing combinators to write your implementation.
 * The function is represented as an extension method for convenience.
 *
 * Tip: This can be solved by using fold along with the write
 * combinator in STArray.
 */
package chapter14.exercises.ex1

import chapter14.sec2.ST
import chapter14.sec2.STArray
import utils.SOLUTION_HERE

//tag::init[]
fun <S, A> STArray<S, A>.fill(xs: Map<Int, A>): ST<S, Unit> =

    SOLUTION_HERE()
//end::init[]
