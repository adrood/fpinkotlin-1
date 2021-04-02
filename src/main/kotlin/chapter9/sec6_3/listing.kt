package chapter9.sec6_3

import arrow.core.lastOrNone
import chapter9.sec5_1.Listing.Location
import chapter9.sec5_2.ParseError
import chapter9.sec6_2.Failure
import chapter9.sec6_2.Parser
import chapter9.sec6_2.Result
import chapter9.sec6_2.Success

infix fun <T> T.cons(la: List<T>): List<T> = listOf(this) + la

//tag::init1[]
// Listing 9.13
// Extension function to push an error onto the ParseError stack head
fun ParseError.push(loc: Location, msg: String): ParseError =
    this.copy(stack = Pair(loc, msg) cons this.stack)
//end::init1[]

//tag::init2[]
// Listing 9.14
// Implementation of scope that records errors using push
fun <A> scope(msg: String, pa: Parser<A>): Parser<A> =
    { state -> pa(state).mapError { pe -> pe.push(state, msg) } }
//end::init2[]

//tag::init3[]
// Listing 9.15
// Extension function to map ParseError on Result failure
fun <A> Result<A>.mapError(f: (ParseError) -> ParseError): Result<A> =
    when (this) {
        is Success -> this
        is Failure -> Failure(f(this.get))
    }
//end::init3[]

//tag::init4[]
// Listing 9.16
// Implementation of tag that records error using tag
fun <A> tag(msg: String, pa: Parser<A>): Parser<A> =
    { state ->
        pa(state).mapError { pe ->
            // Calls a helper method on ParseError, also named tag
            pe.tag(msg) // <1>
        }
    }
//end::init4[]

//tag::init5[]
// Listing 9.17
// Extension function to tag ParseError on Result failure
fun ParseError.tag(msg: String): ParseError {

    // Gets the last element of the stack or None is the stack is empty
    val latest = this.stack.lastOrNone() // <1>

    // Use its location of present
    val latestLocation = latest.map { it.first } // <2>

    // Assemble a new ParseError with only this location and
    // the tag message
    return ParseError(latestLocation.map { Pair(it, msg) }.toList()) // <3>
}
//end::init5[]
