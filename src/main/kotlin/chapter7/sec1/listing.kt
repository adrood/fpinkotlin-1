package chapter7.sec1

import arrow.core.extensions.list.foldable.firstOption
import arrow.core.extensions.list.foldable.foldLeft
import arrow.core.getOrElse

fun <A, B> parMap(ps: List<A>, f: (A) -> B): Par<List<B>> = TODO()

val inputList = emptyList<Int>()

val f = { a: Int -> a * 2 }

//tag::init1[]
val outputList = parMap(inputList, f)
//end::init1[]

val sum1 = {
    //tag::init2[]
    fun sum(ints: List<Int>): Int =
        ints.foldLeft(0) { a, b -> a + b }
    //end::init2[]
}

//tag::splitat[]
fun <A> List<A>.splitAt(idx: Int): Pair<List<A>, List<A>> = // <1>
    this.subList(0, idx) to
        this.subList(idx, this.size)
//end::splitat[]

val sum2 = {
    //tag::init3[]
    // Using our new data type to assimilate parallellism
    fun sum(ints: List<Int>): Int =
        if (ints.size <= 1)
        // Deal with cases of 1 or 0 ints, using Arrow
        // extension method firstOption, like headOption
        // in Chapter 3
            ints.firstOption().getOrElse { 0 } // <1>
        else {
            // Split and destructure the list into two, using
            // helper extension method splitAt
            val (l, r) = ints.splitAt(ints.size / 2) // <2>
            // Recursive call to sum for both l and r and sum them up
            sum(l) + sum(r) // <3>
        }
    //end::init3[]
}

//tag::init4[]
// A new data type to contain a result
class Par<A>(val get: A) // <1>

// Create a unit of parallellism from unevaluated A
fun <A> unit(a: () -> A): Par<A> = Par(a()) // <2>

// Extract the evaluated result of A
fun <A> get(a: Par<A>): A = a.get // <3>

//end::init4[]
//tag::map2[]
fun map2(
    sum: Par<Int>,
    sum1: Par<Int>,
    function: (Int, Int) -> Int
): Par<Int> = TODO()

//end::map2[]
//tag::fork[]
fun <A> fork(a: () -> Par<A>): Par<A> = TODO()

//end::fork[]
// Listing 7.6. A strict unit and fork can be combined to form a lazy
// variant of unit.
val listing = {
    //tag::lazyunit[]
    fun <A> unit(a: A): Par<A> = Par(a)

    fun <A> lazyUnit(a: () -> A): Par<A> =
        fork { unit(a()) }
    //end::lazyunit[]
}

//tag::run[]
fun <A> run(a: Par<A>): A = TODO()
//end::run[]

val sum3 = {
    //tag::init5[]
    fun sum(ints: List<Int>): Int =
        if (ints.size <= 1)
            ints.firstOption().getOrElse { 0 }
        else {
            val (l, r) = ints.splitAt(ints.size / 2)
            // Compute left side of list in context of Par
            val sumL: Par<Int> = unit { sum(l) } // <1>
            // Compute right side of list in context of Par
            val sumR: Par<Int> = unit { sum(r) } // <2>
            // Extract Int results from either Par and sum them
            sumL.get + sumR.get // <3>
        }
    //end::init5[]
}

val l = emptyList<Int>()
val r = emptyList<Int>()
val evaluate = {
    //tag::init6[]
    unit { l }.get + unit { r }.get
    //end::init6[]
}

val sum4 = {
    //tag::init7[]
    fun sum(ints: List<Int>): Par<Int> =
        if (ints.size <= 1)
            unit { ints.firstOption().getOrElse { 0 } }
        else {
            val (l, r) = ints.splitAt(ints.size / 2)
            map2(sum(l), sum(r)) { lx: Int, rx: Int -> lx + rx }
        }
    //end::init7[]

    val trace = {
        //tag::init8[]
        // Unevaluated expression
        sum(listOf(1, 2, 3, 4)) // <1>

        // Substitute sum with its definition of map2
        map2(
            sum(listOf(1, 2)),
            sum(listOf(3, 4))
        ) { i: Int, j: Int -> i + j } // <2>

        // Substitute left argument with its definition map2
        map2(
            map2(
                sum(listOf(1)),
                sum(listOf(2))
            ) { i: Int, j: Int -> i + j }, // <3>
            sum(listOf(3, 4))
        ) { i: Int, j: Int -> i + j }

        // Substitute left sum expressions with their results
        map2(
            map2(
                unit { 1 },
                unit { 2 }
            ) { i: Int, j: Int -> i + j }, // <4>
            sum(listOf(3, 4))
        ) { i: Int, j: Int -> i + j }

        // Substitution of right-hand side with its definition
        map2(
            map2(
                unit { 1 },
                unit { 2 }
            ) { i: Int, j: Int -> i + j },
            map2(
                sum(listOf(3)),
                sum(listOf(4))
            ) { i: Int, j: Int -> i + j } // <5>
        ) { i: Int, j: Int -> i + j }
        //end::init8[]
    }

    // Listing 7.5 Strict construction of the description results in
    // a full tree of operations
    val trace2 = {
        //tag::init9[]
        map2(
            map2(
                unit { 1 },
                unit { 2 }) { i: Int, j: Int -> i + j },
            map2(
                unit { 3 },
                unit { 4 }) { i: Int, j: Int -> i + j }
        ) { i: Int, j: Int -> i + j }
        //end::init9[]
    }

    val listing = {
        //tag::init10[]
        map2(
            unit { 1 },
            unit { 2 }
        ) { i: Int, j: Int -> i + j }
        //end::init10[]
    }
}

val sum5 = {
    //tag::init11[]
    fun sum(ints: List<Int>): Par<Int> =
        if (ints.size <= 1)
            unit { ints.firstOption().getOrElse { 0 } }
        else {
            val (l, r) = ints.splitAt(ints.size / 2)
            map2(
                fork { sum(l) },
                fork { sum(r) }
            ) { lx: Int, rx: Int -> lx + rx }
        }
    //end::init11[]
}
