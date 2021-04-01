package chapter7.sec2

import java.util.concurrent.TimeUnit

val api = {
    //tag::init1a[]
    // Create a computation that immediately results in the value a
    fun <A> unit(a: A): Par<A> // <1>
    //end::init1a[]
        = TODO()

    //tag::init1b[]
    // Combine the results of two parallel computations with a
    // bunary function
    fun <A, B, C> map2(
        a: Par<A>,
        b: Par<B>,
        f: (A, B) -> C
    ): Par<C> // <2>
    //end::init1b[]
        = TODO()

    //tag::init1c[]
    // Mark a computation for concurrent evaluation by run
    fun <A> fork(a: () -> Par<A>): Par<A> // <3>
    //end::init1c[]
        = TODO()

    //tag::init1d[]
    // Wrap expression a for concurrent evaluation by run
    fun <A> lazyUnit(a: () -> A): Par<A> // <4>
    //end::init1d[]
        = TODO()

    //tag::init1e[]
    // Fully evaluate a given Par, spawning computations and
    // extracting value
    fun <A> run(a: Par<A>): A // <5>
    //end::init1e[]
        = TODO()
    //end::init1[]
}

//tag::executor[]
// Listing 7.7. Executor API represented in Kotlin
interface Callable<A> {
    fun call(): A
}

interface Future<A> {
    fun get(): A
    fun get(timeout: Long, timeUnit: TimeUnit): A
    fun cancel(evenIfRunning: Boolean): Boolean
    fun isDone(): Boolean
    fun isCancelled(): Boolean
}

interface ExecutorService {
    fun <A> submit(c: Callable<A>): Future<A>
}
//end::executor[]

val runexec = {
    class Par<A>

    //tag::runexec[]
    fun <A> run(es: ExecutorService, a: Par<A>): A = TODO()
    //end::runexec[]
}

// This typealias must live at the top level
//tag::paralias[]
typealias Par<A> = (ExecutorService) -> Future<A>
//end::paralias[]

//tag::runexec2[]
fun <A> run(es: ExecutorService, a: Par<A>): Future<A> = a(es)
//end::runexec2[]
