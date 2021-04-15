/**
 * Using flatMap and any other combinators, write the context-sensitive
 * parser we couldn't express earlier. The result should be a Parser<Int>
 * that returns the number of characters read.You can make use of a new
 * primitive called regex to parse digits, which promotes a regular
 * expression string to a Parser<String>
 */
package chapter9.exercises.ex6

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {
    init {

        //tag::init1[]
            SOLUTION_HERE()
        //end::init1[]
    }
}
