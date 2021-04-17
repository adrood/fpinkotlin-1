/**
 * Write a property called maxProp to verify the behavior of List.sorted,
 * which you can use to sort (among other things) a List<Int>.
 */
package chapter8.solutions.ex14

import arrow.core.extensions.list.foldable.exists
import chapter8.Gen
import chapter8.Prop.Companion.forAll
import chapter8.SGen
import chapter8.sec4_1.run

val smallInt = Gen.choose(-10, 10)

fun List<Int>.prepend(i: Int) = listOf(i) + this

//tag::init[]
val maxProp = forAll(SGen.listOf(smallInt)) { ns ->
    val nss = ns.sorted()
    // List may be empty
    nss.isEmpty() or // <1>
        // List may only have a single element
            (nss.size == 1) or // <2>
            nss.zip(nss.prepend(Int.MIN_VALUE))
                .foldRight(true, { p, b ->
                    val (pa, pb) = p
                    // List must be ordered in ascending order
                    b && (pa >= pb)
                }) and // <3>
        // List must contain all elements of unsorted list
            nss.containsAll(ns) and // <4>
        // List may not contain any elements that are not in unsorted list
            !nss.exists { !ns.contains(it) } // <5>
}
//end::init[]

fun main() {
    run(maxProp)
}
