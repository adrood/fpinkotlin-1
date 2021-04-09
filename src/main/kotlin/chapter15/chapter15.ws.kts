import chapter15.sec2.liftOne
import chapter15.sec2.toList
import chapter5.Stream

val p = liftOne<Int, Int> { it * 2  }
p(Stream.of(1, 2, 3, 4, 5)).toList()

// As we can see, this Process just waits for one element,
// emits it, and then stops.