import chapter5.sec1.a
import chapter5.sec1.lazyIf
import chapter5.sec1.maybeTwice
import chapter5.sec1.maybeTwice2
import chapter5.sec1.useit

// Apparently Kotlin doesn't have a List.of method
//List.of(1, 2, 3, 4)

// Section 1
// does not print anything
false && { println("!!"); true }.invoke()

// does not print anything either
true || { println("!!"); false }.invoke()

println("Hello")

maybeTwice2(true, { println("hi"); 1 + 41})

println("Hello after")

//val x = maybeTwice2(true, { println("hi"); 1 + 41 })

//useit()
