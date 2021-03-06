package chapter5.sec1

import chapter3.List
import kotlin.system.exitProcess

fun <A, B> List<A>.map(f: (A) -> B): List<B> = TODO()
fun <A> List<A>.filter(f: (A) -> Boolean): List<A> = TODO()

/**
 * Listing 5.1.
 * Evaluation trace of operations on a strict list implementation.
 */
val listing = {
    //tag::init1[]
    List.of(1, 2, 3, 4)
        .map { it + 10 }.filter { it % 2 == 0 }.map { it * 3 }
    List.of(11, 12, 13, 14)
        .filter { it % 2 == 0 }.map { it * 3 }
    List.of(12, 14)
        .map { it * 3 }
    List.of(36, 42)
    //end::init1[]
}

//tag::init2[]
fun square(x: Double): Double = x * x
//end::init2[]

val input = ""

//tag::init3[]
val result = if (input.isEmpty()) exitProcess(-1) else input
//end::init3[]

val a = 10

//tag::init4[]
fun <A> lazyIf(
    cond: Boolean,
    // The function parameter type for a lazy value type A is '() -> A'
    onTrue: () -> A, //<1>
    onFalse: () -> A
): A = if (cond) onTrue() else onFalse()

val y = lazyIf((a < 22),
    // The function literal syntax for creating a '() -> A'
    { println("a") }, // <2>
    { println("b") }
)
//end::init4[]

//tag::init5[]
fun maybeTwice(b: Boolean, i: () -> Int) =
    if (b) i() + i() else 0
//end::init5[]

//tag::init6[]
fun maybeTwice2(b: Boolean, i: () -> Int) {
    val j: Int by lazy(i)
    if (b) j + j else 0
}
//end::init6[]

fun expensiveOp(): Int = TODO()

//tag::init7[]
// The 'by' keyword is used to bind the Lazy<Int> returned by lazy to x
val x: Int by lazy { expensiveOp() } // <1>

fun useit() =
    // When x is evaluated in the conditional statement, the call to
    // expensiveOp will be made and the result cached
    if (x > 10) "hi" // <2>
    // Cached value is used instead of making another call to
    // expensiveOp
    else if (x == 0) "zero" // <3>
    else ("lo")
//end::init7[]
