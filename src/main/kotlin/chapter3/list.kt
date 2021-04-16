package chapter3

//Singly Linked List data structure

//tag::example[]
// Sealed definition of data type
sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> empty(): List<A> = Nil
    }

    tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
        when (xs) {
            is Nil -> z
            is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
        }

//tag::impls[]
    fun reverse(): List<A> =
        foldLeft(this, empty(), { t: List<A>, h: A -> Cons(h, t) })
}

// The Nil implementation of List
object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
} // <2>

// The Cons implementation of List
data class Cons<out A>(
    val head: A,
    val tail: List<A>
) : List<A>() // <3>
//end::impls[]
//end::example[]
