package chapter3.sec1

//tag::init[]
// Sealed definition of data type
sealed class List<out A> // <1>

// The Nil implementation of List
object Nil : List<Nothing>() // <2>

// The Cons implementation of List
data class Cons<out A>(val head: A, val tail: List<A>) : List<A>() // <3>
//end::init[]
