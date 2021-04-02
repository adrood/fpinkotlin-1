package chapter8.sec4_6

import chapter8.Falsified
import chapter8.Passed
import chapter8.Prop

val listing5 = {
    //tag::init[]
    // A simple implementation of a check primitive is to construct
    // a Prop that ignores the number of test cases
    fun check(p: () -> Boolean): Prop =
        Prop { _, _, _ ->
            if (p()) Passed
            else Falsified("()", 0)
        }
    //end::init[]
}
