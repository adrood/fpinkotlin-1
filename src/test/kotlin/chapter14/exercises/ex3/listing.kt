/**
 * Give the same treatment to scala.collection.mutable.HashMap as we've
 * given here to references and arrays. Come up with a minimal set of
 * primitive combinators for creating and manipulating hash maps.
 *
 * Tip: Follow the same pattern as we used to write STArray.
 */
package chapter14.exercises.ex3

import chapter10.Option
import chapter14.boilerplate.ST
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap

//tag::init[]
abstract class STMap<S, K, V> @PublishedApi internal constructor() {

    //SOLUTION_HERE()
}
//end::init[]
