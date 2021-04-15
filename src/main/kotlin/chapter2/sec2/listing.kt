package chapter2.sec2

//tag::init1[]
fun findFirst(ss: Array<String>, key: String): Int {
    tailrec fun loop(n: Int): Int =
        when {
            // If the end of the loop has been reached without finding
            // the key, return -1
            n >= ss.size -> -1 // <1>
            // If the key is found, return its position
            ss[n] == key -> n // <2>
            // Recursively call the function, incrementing the counter
            else -> loop(n + 1) // <3>
        }
    // Initialize the loop with count 0
    return loop(0) // <4>
}
//end::init1[]

// tag::init2[]

// Listing 2.4.
// Polymorphic function to find an element in an array.

// Operate on an array of A, take a predicate function operating
// on individual elements of A.
fun <A> findFirst(xs: Array<A>, p: (A) -> Boolean): Int { // <1>
    tailrec fun loop(n: Int): Int =
        when {
            // If the end of the loop has been reached
            // without finding the key, return -1
            n >= xs.size -> -1 // <1>
            // Apply the predicate function to the array element
            // If the key is found, return its position
            p(xs[n]) -> n // <2>
            // Recursively call the function,
            // incrementing the counter.
            else -> loop(n + 1) // <3>
        }
    // Initialise the loop with count 0.
    return loop(0) // <4>
}
//end::init2[]
