/**
 * Assuming the following representation, use check to implement and as
 * a method of Prop
 *
 * An anonymous instance of Prop is returned that is based on this and the
 * property p that is passed in.
 */
package chapter8.solutions.ex3

//tag::init[]
interface Prop {
    fun check(): Boolean
    fun and(p: Prop): Prop {
        val checked = this.check() && p.check()
        return object : Prop {
            override fun check() = checked
        }
    }
}
//end::init[]
