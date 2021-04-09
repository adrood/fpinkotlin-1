import chapter3.sec1.Cons
import chapter3.sec1.Nil
import chapter3.sec1.List as List1
import chapter3.List

val ex1: List1<Double> = Nil
val ex2: List1<Int> = Cons(1, Nil)
val ex3: List1<String> = Cons("a", Cons("b", Nil))

List.of(1, 2)

val p = Pair("Bob", 42)
p
p.first
val (first, second) = p
first
second
