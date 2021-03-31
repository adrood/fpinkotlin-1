package chapter8.sec1

import arrow.core.extensions.list.foldable.firstOption
import arrow.core.lastOrNone
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll

fun main() {
    //tag::init1[]
    // A generator of lists containing integers between 0 and 100
    val intList = Gen.list(Gen.choose(0, 100)) // <1>

    // A valid property that specifies the behavior of the
    // List.reversed method
    forAll(intList) { // <2>
        // Check that reversing a list twice gives back the original list
        (it.reversed().reversed() == it) and // <3>
            // Check that the first element becomes the last
            // element after reversal
            (it.firstOption() == it.reversed().lastOrNone()) // <4>
    }

    // Second property that fails under most conditions
    forAll(intList) { // <5>
        it.reversed() == it
    }
    //end::init1[]
}
