package chapter2

object Listing_2_2 {

    //tag::factorial[]
    fun factorial(i: Int): Int {
        // An inner or local function definition
        fun go(n: Int, acc: Int): Int = // <1>
            if (n <= 0) acc
            else go(n - 1, n * acc)
        // Calling the local function.
        return go(i, 1) // <2>
    }
    //end::factorial[]
}

object Listing_2_2_1 {

    //tag::factorial2[]
    fun factorial(i: Int): Int {
        // The tailrec modifier instructs the compiler to
        // eliminate tail calls
        tailrec fun go(n: Int, acc: Int): Int = // <1>
            if (n <= 0) acc
            else go(n - 1, n * acc) // <2>
        // The function's final declaration(?) is in tail position
        return go(i, 1)
    }
    //end::factorial2[]
}
