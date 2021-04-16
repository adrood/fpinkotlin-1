import chapter3.Cons
import chapter3.Nil
import chapter3.List
import chapter3.sec2.List as List32

val ex1: List<Double> = Nil
val ex2: List<Int> = Cons(1, Nil)
val ex3: List<String> = Cons("a", Cons("b", Nil))

val xs = List.of(1, 2)
;

// Inside the companion object of List:
// fun <A> tail(xs: List<A>): List<A> = TODO()

// List32.sum(xs)

// fun <A> List<A>.tail(): List<A> =
//     when (this) {
//         is Cons -> this.tail
//         is Nil -> Nil
//     }
//
// xs.tail()