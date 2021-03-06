package chapter8.sec4_5

import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll

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
