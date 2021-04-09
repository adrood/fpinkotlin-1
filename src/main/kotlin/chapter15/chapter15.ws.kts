import chapter15.sec2.filter
import chapter15.sec2.liftOne
import chapter15.sec2.sum
import chapter15.sec2.toList
import chapter15.sec3_3.Process.Companion.lift
import chapter5.Stream

val p = liftOne<Int, Int> { it * 2  }
p(Stream.of(1, 2, 3, 4, 5)).toList()

// As we can see, this Process just waits for one element,
// emits it, and then stops.

val units = Stream.continually(Unit)
units

// lift<Unit, Int> { _ -> 1 } (units)

val even = filter<Int> {it % 2 == 0 }
even(Stream.of(1, 2, 3, 4, 5)).toList()

sum()(Stream.of(1.0, 2.0, 3.0, 4.0)).toList()

