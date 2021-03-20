package chapter2

object Listing_2_5 {
    //tag::init[]
    // Polymorphic function to find an element in an array.
    // Operate on an array of A, take a predicate function operating
    // on individual elements of A.
    fun <A> findFirst(xs: Array<A>, p: (A) -> Boolean): Int { // <1>
        tailrec fun loop(n: Int): Int =
            when {
                n >= xs.size -> -1
                // Apply the predicate function to the array element.
                p(xs[n]) -> n // <2>
                else -> loop(n + 1)
            }
        return loop(0)
    }
    //end::init[]
}
