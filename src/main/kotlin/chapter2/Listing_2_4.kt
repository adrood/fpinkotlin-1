package chapter2

object Listing_2_4 {

    //tag::init[]
    // Monomorphic function to find a string in an array
    fun findFirst(ss: Array<String>, key: String): Int {
        tailrec fun loop(n: Int): Int =
            when {
                // If the end of the loop has been reached
                // without finding the key, return -1
                n >= ss.size -> -1 // <1>
                // If the key is found, return its position
                ss[n] == key -> n // <2>
                // Recursively call the function,
                // incrementing the counter.
                else -> loop(n + 1) // <3>
            }
        // Initialise the loop with count 0.
        return loop(0) // <4>
    }
    //end::init[]
}
