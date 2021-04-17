package chapter8.sec2_13

import chapter8.RNG
import chapter8.Result
import chapter8.TestCases

//tag::init[]
// Listing 8.4. Supply an instance of RNG for Prop to allow
// test case generation (Compare with sec2_11
data class Prop(val check: (TestCases, RNG) -> Result)
//end::init[]
