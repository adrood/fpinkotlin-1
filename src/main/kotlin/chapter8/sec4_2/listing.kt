package chapter8.sec4_2

import chapter7.sec4.map
import chapter7.sec4.unit

val listing = {
    //tag::init[]
    // 8.4.2. Writing a test suite for parallel computations
    map(unit(1)) { it + 1 } == unit(2)
    //end::init[]
}
