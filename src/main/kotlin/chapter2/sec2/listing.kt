package chapter2.sec2

object Listing_2_4 {

    //tag::init[]
    // Polymorphic function to find an element in an array.
    // Operate on an array of A, take a predicate function operating
    // on individual elements of A.
    fun <A> findFirst(xs: Array<A>, p: (A) -> Boolean): Int { // <1>
        tailrec fun loop(n: Int): Int =
            when {
                // If the end of the loop has been reached
                // without finding the key, return -1
                n >= xs.size -> -1 // <1>
                // If the key is found, return its position
                p(xs[n]) -> n // <2>
                // Recursively call the function,
                // incrementing the counter.
                else -> loop(n + 1) // <3>
            }
        // Initialise the loop with count 0.
        return loop(0) // <4>
    }
    //end::init[]
}
//end::init2[]
