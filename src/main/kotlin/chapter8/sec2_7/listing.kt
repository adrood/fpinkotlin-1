package chapter8.sec2_7

import arrow.core.Either

//tag::init[]
// We prefer type aliases instead of simple types like String, Int, or
// Double because we can assign meaningful names to them.
typealias SuccessCount = Int

interface Prop {
    fun check(): Either<String, SuccessCount>
    fun and(p: Prop): Prop
}
//end::init[]
