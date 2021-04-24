import java.lang.Exception
import java.lang.StringBuilder

// Section 1.3
val x  = "Hello World"

val r1 = x.reversed()
r1

val r2 = x.reversed()
r2

// Replace all occurrences of x with the expression
// referenced by x

val r3 = "Hello World".reversed()
r3

val r4 = "Hello World".reversed()
r4

// Now let's look at a function that is not referentially
// transparent

val y = StringBuilder("Hello")
y

val z = y.append(", World")
z

// Calling toString() multiple times always yields the
// same result.
val s1 = z.toString()
s1

val s2 = z.toString()
s2

// Substitute the call to append(), like we did earlier,
// replacing all occurrences of z with the expression
// referenced by z:

val s3 = y.append(", World").toString()
s3

val s4 = y.append(", World").toString()
s4
