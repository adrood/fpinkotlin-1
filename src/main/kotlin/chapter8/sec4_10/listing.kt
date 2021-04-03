package chapter8.sec4_10

import chapter7.sec4.Par
import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun <A> weighted(
    pga: Pair<Gen<A>, Double>,
    pgb: Pair<Gen<A>, Double>
): Gen<A> = TODO()

fun <A, B, C> map2(ga: Gen<A>, gb: Gen<B>, f: (A, B) -> C): Gen<C> =
    TODO()

//tag::init1[]
// A weighted generator of executor services
val ges: Gen<ExecutorService> = weighted( // <1>
    Gen.choose(1, 4).map {
        // Create a fixed thread pool 75% of the time
        Executors.newFixedThreadPool(it)
    } to .75, // <2>
    Gen.unit(
        // Create an unbounded thread pool 25% of the time
        Executors.newCachedThreadPool()
    ) to .25) // <3>

fun <A> forAllPar(ga: Gen<A>, f: (A) -> Par<Boolean>): Prop =
    forAll(
        // Create a Pair<Gen<ExecutorService>,Gen<A>> using the to keyword
        map2(ges, ga) { es, a -> es to a } // <4>
    ) { (es, a) -> f(a)(es).get() }
//end::init1[]
