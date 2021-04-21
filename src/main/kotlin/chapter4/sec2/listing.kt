package chapter4.sec2

sealed class List<out A>

private fun <A> length(xs: List<A>): Int = TODO()

private fun List<Double>.sum(): Double = TODO()

private fun List<Double>.isEmpty(): Boolean = TODO()

fun <A> List<A>.size(): Int = TODO()

val listing1 = {
    //tag::init1[]
    fun mean(xs: List<Double>): Double =
        if (xs.isEmpty())
            // An Arithmetic exception is thrown on xs being empty
            throw ArithmeticException("mean of emtpy list!") // <1>
        // Otherwise return the valid result
        else xs.sum() / length(xs) // <2>
    //end::init1[]
}

val listing2 = {
    //tag::init2[]
    fun mean(xs: List<Double>, onEmpty: Double) =
        // A default value is provided on xs being empty
        if (xs.isEmpty()) onEmpty // <1>
        // Otherwise return the valid result
        else xs.sum() / xs.size() // <2>
    //end::init2[]
}

// No Exercises in this section
