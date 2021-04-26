/**
 * Write a function to convert a Stream to a List, which will force its
 * evaluation and let you look at it in the REPL as well as perform
 * assertions in the unit tests provided in the source repository. You
 * can convert to the singly-linked List type that we developed in
 * Chapter 3 of this book. You can implement this and other functions that
 * operate on a Stream using extension methods.
 *
 * Tip: Although a simple recursive solution will work, a stack overflow
 * could occur on larger streams. An improved solution is to do this as
 * a tail-recursive function with a list reversal at the end.
 */
package chapter5.exercises.ex1

import chapter3.List
import chapter3.reverse
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.solutions.ex1.toListUnsafe
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    //Unsafe! Naive solution could cause a stack overflow.
    fun <A> Stream<A>.toListUnsafe(): List<A> =

        SOLUTION_HERE()

    fun <A> Stream<A>.toList(): List<A> =

        SOLUTION_HERE()
    //end::init[]

    "Stream.toList" should {
        "!force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toListUnsafe() shouldBe List.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
