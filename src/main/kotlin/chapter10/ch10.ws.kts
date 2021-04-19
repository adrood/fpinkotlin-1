import arrow.core.extensions.list.foldable.foldLeft
import chapter10.sec1.stringMonoid
import chapter10.sec6.intAdditionMonoid
import chapter10.sec6.m

val m1 = mapOf("o1" to mapOf("i1" to 1, "i2" to 2))
val m2 = mapOf("o1" to mapOf("i3" to 3))

m.combine(m1, m2)

// Section 10.2
val words = listOf<String>("Hic", "Est", "Index")

words.foldRight(stringMonoid.nil, stringMonoid::combine)

words.foldLeft(stringMonoid.nil, stringMonoid::combine)

words.foldLeft("") { a, b -> a + b } == (("" + "Hic") + "Est") + "Index"
words.foldRight("") { a, b -> a + b } == "Hic" + ("Est" + "Index" + "")

/*
interface Foldable<F<A>> {
    // some methods
}

object ListFoldable : Foldable<List<A>>
 */

// Section 10.6.2
// Listing 10.4
// Determine the mean of a list by calculating the length and sum
// simultaneously.

// productMonoid not accessable within this sheet
// val m = productMonoid<Int, Int> (intAdditionMonoid, intAdditionMonoid)