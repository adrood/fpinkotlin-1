package chapter8.sec4.listing5

import chapter8.sec3.listing3.Gen
import chapter8.sec3.listing3.Prop
import chapter8.sec3.listing3.Prop.Companion.forAll

val listing4 = {
    //tag::init[]
    // Pass in a non-strict value
    fun check(p: () -> Boolean): Prop { // <1>
        // Result is memoized to avoid recomputation
        val result by lazy { p() } // <2>
        return forAll(Gen.unit(Unit)) {
            result
        }
    }
    //end::init[]
}
