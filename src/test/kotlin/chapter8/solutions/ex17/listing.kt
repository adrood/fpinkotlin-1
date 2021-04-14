/**
 * Come up with some other properties that takeWhile should satisfy. Can
 * you think of a good property expressing the relationship between
 * takeWhile and dropWhile?
 */
package chapter8.solutions.ex17

/*
//tag::rel[]
l.takeWhile(f) + l.dropWhile(f) == l
//end::rel[]
*/

fun main() {
    //tag::list[]
    val l = listOf(1, 2, 3, 4, 5)
    val f = { i: Int -> i < 3 }
    val res0 = l.takeWhile(f) + l.dropWhile(f)

    assert(res0 == l)
    //end::list[]
}
