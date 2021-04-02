package chapter4

import arrow.core.Either
import arrow.core.extensions.fx
import chapter4.Listing_4_4.insuranceRateQuote

object Listing_4_7 {
    //tag::init[]
    // Add pareToInt extension method to String
    suspend fun String.parseToInt(): Either<Throwable, Int> = // <1>
        // Use the Either.catch method to produce
        // an Either<Throwable, Int>
        Either.catch { this.toInt() } // <2>

    // Method marked suspended, meaning its
    // child process could block
    suspend fun parseInsuranceRateQuote( // <3>
        age: String,
        numberOfSpeedingTickets: String
    ): Either<Throwable, Double> {
        // Use extension method to produce an Either
        val ae = age.parseToInt() // <4>
        val te = numberOfSpeedingTickets.parseToInt()
        // Open for-comporehension with Either.fx
        return Either.fx { // <5>
            // flatMap right-biased either by destructuring
            val a = ae.bind() // <6>
            val t = te.bind()
            // Return final evaluation of insuranceRateQuote as
            // Either.Right on success
            insuranceRateQuote(a, t) // <7>
        }
    }
    //end::init[]
}
