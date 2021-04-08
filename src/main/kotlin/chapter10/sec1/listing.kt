package chapter10.sec1

//tag::init1[]
interface Monoid<A> {
    // Satisfies the law of associativity,
    // combine(combine(x,y),z) == combine(x, combine(y,z))
    fun combine(a1: A, a2: A): A // <1>
    // Satisifies the law of identity,
    // combine(x,nil) == x and combine(nil, x) == x
    val nil: A // <2>
}
//end::init1[]

//tag::init2[]
// An example instance of this interface is the String monoid.
val stringMonoid = object : Monoid<String> {

    override fun combine(a1: String, a2: String): String = a1 + a2

    override val nil: String = ""
}
//end::init2[]

//tag::init3[]
// List concatenation also forms a monoid.
// This method is able to generate a monoid for any type A
fun <A> listMonoid(): Monoid<List<A>> = object : Monoid<List<A>> {

    override fun combine(a1: List<A>, a2: List<A>): List<A> = a1 + a2

    override val nil: List<A> = emptyList()
}
//end::init3[]
