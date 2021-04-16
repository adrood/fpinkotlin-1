import chapter5.sec1.a
import chapter5.sec1.lazyIf
import chapter5.sec1.maybeTwice
import chapter5.sec1.useit

// Apparently Kotlin doesn't have a List.of method
//List.of(1, 2, 3, 4)

// does not print anything
false && { println("!!"); true }.invoke()

// does not print anything either
true || { println("!!"); false }.invoke()

maybeTwice(true, { println("hi"); 1 + 41})

useit()
