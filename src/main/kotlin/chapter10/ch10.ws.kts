import arrow.core.extensions.list.foldable.foldLeft
import chapter10.sec1.stringMonoid
import chapter10.sec6.m

val m1 = mapOf("o1" to mapOf("i1" to 1, "i2" to 2))
val m2 = mapOf("o1" to mapOf("i3" to 3))

m.combine(m1, m2)


val words = listOf<String>("Hic", "Est", "Index")

words.foldRight(stringMonoid.nil, stringMonoid::combine)

words.foldLeft(stringMonoid.nil, stringMonoid::combine)

/*
interface Foldable<F<A>> {
    // some methods
}

object ListFoldable : Foldable<List<A>>
 */

