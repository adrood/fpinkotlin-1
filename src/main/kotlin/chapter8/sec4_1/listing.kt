package chapter8.sec4_1

import arrow.core.extensions.list.foldable.exists
import chapter8.Falsified
import chapter8.Passed
import chapter8.RNG
import chapter8.SimpleRNG
import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll
import chapter8.SGen

//tag::init2[]
// Listing 8.9.
// A convenience method for running properties using sensible defaults
fun run(
    p: Prop,
    // Set default maximum size of test cases to 100
    maxSize: Int = 100, // <1>
    // Set default amount of test cases to run at 100
    testCases: Int = 100, // <2>
    // Provide a simple random number generator ready for action
    rng: RNG = SimpleRNG(System.currentTimeMillis()) // <3>
): Unit =
    // Calling check directly on a Prop
    when (val result = p.check(maxSize, testCases, rng)) {
        // Print error message to standard out in case of failure
        is Falsified -> // <4>
            println(
                "Falsified after ${result.successes}" +
                    "passed tests: ${result.failure}"
            )
        // Print success message to standard out in case tests pass
        is Passed -> // <5>
            println("OK, passed $testCases tests.")
    }
//end::init2[]

fun main() {
    //tag::init1[]
    // Listing 8.8. Property specifying maximum value in a list
    val smallInt = Gen.choose(-10, 10)

    val maxProp = forAll(SGen.listOf(smallInt)) { ns ->
        val mx = ns.max()
            ?: throw IllegalStateException("max on empty list")
        // No value greater than mx should exist in ns
        !ns.exists { it > mx } // <1>
    }
    //end::init1[]
    run(maxProp)
}
