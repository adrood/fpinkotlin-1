/**
 * See what happens when you pass Nil and Cons themselves to foldRight,
 * like in fun main() below?
 *
 * What do you think this says about the relationship between foldRight and
 * the data constructors of Lis?
 *
 * Tip: Now follow on with each subsequent call to foldRight.
 * The first step should be represented as: (see trace)
 */
package chapter3.exercises.ex7

import chapter3.Cons
import chapter3.List
import chapter3.Nil

// tag::init[]
fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> f(xs.head, foldRight(xs.tail, z, f))
    }

val f = { x: Int, y: List<Int> -> Cons(x, y) }
val z = Nil as List<Int>

val trace = {
    foldRight(List.of(1, 2, 3), z, f)
    Cons(1, foldRight(List.of(2, 3), z, f))
    Cons(1, Cons(2, foldRight(List.of(3), z, f)))
    Cons(1, Cons(2, Cons(3, foldRight(List.empty(), z, f))))
    Cons(1, Cons(2, Cons(3, Nil)))
}

fun main() {
    // tag::init[]
    println(foldRight(
        Cons(1, Cons(2, Cons(3, Nil))),
        Nil as List<Int>,
        { x, y -> Cons(x, y) }
    ))
    // end::init[]
}
