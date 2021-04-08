import arrow.core.extensions.list.foldable.foldLeft
import chapter10.sec1.stringMonoid

val words = listOf<String>("Hic", "Est", "Index")

words.foldRight(stringMonoid.nil, stringMonoid::combine)

words.foldLeft(stringMonoid.nil, stringMonoid::combine)

/*
interface Foldable<F<A>> {
    // some methods
}

object ListFoldable : Foldable<List<A>>
 */

