import chapter5.sec1.a
import chapter5.sec1.lazyIf
import chapter5.sec1.maybeTwice
import chapter5.sec1.maybeTwice2
import chapter5.sec1.useit
import chapter5.sec4.exists
import chapter5.sec4.ones

// Apparently Kotlin doesn't have a List.of method
//List.of(1, 2, 3, 4)

// Section 5.1
// does not print anything
false && { println("!!"); true }.invoke()

// does not print anything either
true || { println("!!"); false }.invoke()

println("Hello")

// maybeTwice2(true, { println("hi"); 1 + 41})

println("Hello after")

//val x = maybeTwice2(true, { println("hi"); 1 + 41 })

//useit()

// Section 5.4

// There is no method take()
//ones().take(5).toList()

// exception: An operation is not implemented.
//ones().exists { it % 2 != 0 }

println("Hello again")

