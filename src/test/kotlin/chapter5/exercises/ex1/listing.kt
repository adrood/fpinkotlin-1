package chapter5.exercises.ex1

import chapter3.List
import chapter5.Stream
import chapter5.Cons
import chapter5.Empty
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    fun <A> Stream<A>.toList(): List<A> =

        SOLUTION_HERE()
    //end::init[]

    "Stream.toList" should {
        "!force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
