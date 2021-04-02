package chapter4

object Listing_4_3 {

    // Apply f to transform value of A to B if the Option is
    // not None
    fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = TODO()

    // Apply f, which may fail, to the Option if the Option is
    // not None
    fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> = TODO()

    // Return a default value if the Option is None
    fun <A> Option<A>.getOrElse(default: () -> A): A = TODO()

    // Convert Some to None if the predicate f is not met
    fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> = TODO()

    //tag::init[]
    data class Employee(
        val name: String,
        val department: String,
        val manager: Option<String>
    )

    fun lookupByName(name: String): Option<Employee> = TODO()

    fun timDepartment(): Option<String> =
        lookupByName("Tim").map { it.department }
    //end::init[]

    //tag::init2[]
    val dept: String = lookupByName("Tim")
        .map { it.department }
        .filter { it != "Accounts" }
        .getOrElse { "Unemployed" }
    //end::init2[]

    //tag::init3[]
    val unwieldy: Option<Option<String>> =
        lookupByName("Tim").map { it.manager }
    //end::init3[]

    //tag::init4[]
    val manager: String = lookupByName("Tim")
        .flatMap { it.manager }
        .getOrElse { "Unemployed" }
    //end::init4[]
}
