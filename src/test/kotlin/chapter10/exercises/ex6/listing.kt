/**
 * The foldMap function can be implemented using either foldLeft or
 * foldRight. But you can also write foldLeft and foldRight using
 * foldMap. Give it a try for fun!
 *
 * The function type (A,B) -> B, when curried is (A) -> (B) -> B.
 * And of course (B) -> B is a monoid for any B (via function composition).
 *
 * Folding to the left is the same except we flip the arguments to the
 * function f to put the B on the correct side. Then we have to also
 * "flip" the monoid so that it operates from left to right.
 *
 * Tip: The type of the function that is passed to foldRight is
 * (A,B) -> B, which can be curried to (A) -> (B) -> B. This is a strong
 * hint that we should use the endofunction monoid, (B) -> B to implement
 * foldRight. The implementation of foldLeft is simply the dual of this
 * operation. Don't be too concerned about efficiency in these
 * implementations
 */
package chapter10.exercises.ex6

import chapter10.dual
import chapter10.endoMonoid
import chapter10.foldMap
import utils.SOLUTION_HERE

//tag::init1[]
fun <A, B> foldRight(la: Sequence<A>, z: B, f: (A, B) -> B): B =

    SOLUTION_HERE()
//end::init1[]

//tag::init2[]
fun <A, B> foldLeft(la: Sequence<A>, z: B, f: (B, A) -> B): B =

    SOLUTION_HERE()
//end::init2[]
