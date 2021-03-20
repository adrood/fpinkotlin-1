package chapter3

//Singly Linked List data structure

//tag::example[]
// Sealed definition of data type
sealed class List<out A> { // <1>
    //tag::comment[]
    // helper functions
    //end::comment[]
    //tag::companion[]

    // Companion object containing functions
    companion object { // <2>

        //tag::of[]
        // Factory helper function
        fun <A> of(vararg aa: A): List<A> { // <3>
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        //end::of[]
        //tag::sum[]
        fun sum(ints: List<Int>): Int =
            when (ints) {
                is Nil -> 0
                is Cons -> ints.head + sum(ints.tail)
            }

        //end::sum[]
        //tag::product[]
        fun product(doubles: List<Double>): Double =
            when (doubles) {
                is Nil -> 1.0
                is Cons ->
                    if (doubles.head == 0.0) 0.0
                    else doubles.head * product(doubles.tail)
            }

        //end::product[]
        //tag::empty[]
        fun <A> empty(): List<A> = Nil
        //end::empty[]
    }
    //end::companion[]
}

//tag::impls[]
// The Nil implementation of List
object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
} // <2>

data class Cons<out A>(
    val head: A,
    val tail: List<A>
    // The Cons implementation of List
) : List<A>() // <3>
//end::impls[]
//end::example[]
