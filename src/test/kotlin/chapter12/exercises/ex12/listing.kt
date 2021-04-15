/**
 * Tip: The treeTraversable con depend on the listTraversable for its
 * functionality. Lifting to, and operating on, Kind<G, A> can be done
 * using an Applicative<G> instance in scope.
 */
package chapter12.exercises.ex12

import arrow.Kind
import chapter10.ForOption
import chapter10.None
import chapter10.OptionOf
import chapter10.Some
import chapter10.fix
import chapter12.Applicative
import chapter12.Cons
import chapter12.ForList
import chapter12.ForTree
import chapter12.List
import chapter12.ListOf
import chapter12.Traversable
import chapter12.Tree
import chapter12.TreeOf
import chapter12.fix
import utils.SOLUTION_HERE

//tag::init1[]
fun <A> optionTraversable(): Traversable<ForOption> =

    SOLUTION_HERE()
//end::init1[]

//tag::init2[]
fun <A> listTraversable(): Traversable<ForList> =

    SOLUTION_HERE()
//end::init2[]

//tag::init3[]
fun <A> treeTraversable(): Traversable<ForTree> =

    SOLUTION_HERE()
//end::init3[]