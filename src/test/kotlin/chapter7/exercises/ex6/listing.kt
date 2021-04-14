package chapter7.exercises.ex6

import chapter7.solutions.ex3.TimedMap2Future
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import utils.SOLUTION_HERE
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture.completedFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

//tag::init[]
fun <A> parFilter(
    sa: List<A>,
    f: (A) -> Boolean
): Par<List<A>> =

    SOLUTION_HERE()
//end::init[]
