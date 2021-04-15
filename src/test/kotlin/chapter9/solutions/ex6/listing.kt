/**
 * Using flatMap and any other combinators, write the context-sensitive
 * parser we couldn't express earlier. The result should be a Parser<Int>
 * that returns the number of characters read.You can make use of a new
 * primitive called regex to parse digits, which promotes a regular
 * expression string to a Parser<String>
 */
package chapter9.solutions.ex6

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl

abstract class Listing : ParserDsl<ParseError>() {
    init {

        //tag::init1[]
        val parser: Parser<Int> = regex("[0-9]+")
            .flatMap { digit: String ->
                val reps = digit.toInt()
                listOfN(reps, char('a')).map { _ -> reps }
            }
        //end::init1[]
    }
}
