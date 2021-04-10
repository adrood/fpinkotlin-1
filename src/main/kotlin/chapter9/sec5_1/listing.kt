package chapter9.sec5_1

import chapter9.ParseError
import chapter9.Parser
import chapter9.Parsers

interface Listing : Parsers<ParseError> {

    fun listing0() {
        //tag::init0[]
        val spaces = string(" ").many()

        string("abra") product spaces product string("cadabra")
        //end::init0[]
    }

    override
    //tag::init1[]
    // A combinator for assigning an error message to a parser
    // If p fails, its ParseError will somehow incorporate msg.
    fun <A> tag(msg: String, p: Parser<A>): Parser<A>
    //end::init1[]

    //tag::init2[]
    // Listing 9.7.
    // Representing PareError in terms of message and location
    data class Location(val input: String, val offset: Int = 0) {

        // Prepare a substring of the input up to where the error occurred
        private val slice by lazy { input.slice(0..offset + 1) } // <1>

        // Calculate the number of lines to the error location
        val line by lazy { slice.count { it == '\n' } + 1 } // <2>

        // Calculate the number of columns to the error location
        val column by lazy {
            when (val n = slice.lastIndexOf('\n')) { // <3>
                -1 -> offset + 1
                else -> offset - n
            }
        }
    }

    fun errorLocation(e: ParseError): Location

    fun errorMessage(e: ParseError): String
    //end::init2[]
}
