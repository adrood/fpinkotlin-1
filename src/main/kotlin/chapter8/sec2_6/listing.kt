package chapter8.sec2_6

//tag::init[]
// Section 8.2.2
interface Prop {
    fun check(): Unit
    fun and(p: Prop): Prop
}
//end::init[]
