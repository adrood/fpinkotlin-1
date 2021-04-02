package chapter4

import chapter3.List

object Listing_4_2_1 {

    private fun <A> length(xs: List<A>): Int = TODO()

    private fun List<Double>.sum(): Double = TODO()

    private fun List<Double>.isEmpty(): Boolean = TODO()

    fun <A> List<A>.size(): Int = TODO()

    //tag::init[]
    fun mean(xs: List<Double>): Double =
        if (xs.isEmpty())
            // An Arithmetic exception is thrown on xs being empty
            throw ArithmeticException("mean of emtpy list!") // <1>
        // Otherwise return the valid result
        else xs.sum() / length(xs) // <2>
    //end::init[]
}
