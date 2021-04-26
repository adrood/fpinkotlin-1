/**
 * It's possible to define the runLog function more generally for any
 * Monad that allows the catching and raising of exceptions. For instance,
 * the Task type mentioned in chapter 13, adds this capability to the
 * IO type. Define this more general version of runLog. Note that this
 * interpreter can't be tail-recursive and relies on the underlying monad
 * for stack safety.
 *
 * Tip: Delegate all monadic behavior to the provided MonadCatch instance.
 */
package chapter15.solutions.ex10

import arrow.Kind
import chapter12.Either
import chapter13.Monad
import chapter15.sec3.Process
import chapter15.sec3.Process.Companion.Await
import chapter15.sec3.Process.Companion.Emit
import chapter15.sec3.Process.Companion.End
import chapter15.sec3.Process.Companion.Halt
import chapter15.sec3.tryP

//tag::init[]
fun <F, O> Process<F, O>.runLog(MC: MonadCatch<F>): Kind<F, Sequence<O>> {

    fun go(cur: Process<F, O>, acc: Sequence<O>): Kind<F, Sequence<O>> =
        when (cur) {
            is Emit ->
                go(cur.tail, acc + cur.head)
            is Halt ->
                when (val e = cur.err) {
                    is End -> MC.unit(acc)
                    else -> throw e
                }
            is Await<*, *, *> -> {
                val re: Kind<F, O> = cur.req as Kind<F, O>
                val rcv: (Either<Throwable, O>) -> Process<F, O> =
                    cur.recv as (Either<Throwable, O>) -> Process<F, O>
                MC.flatMap(MC.attempt(re)) { ei ->
                    go(tryP { rcv(ei) }, acc)
                }
            }
        }

    return go(this, emptySequence())
}

interface MonadCatch<F> : Monad<F> {
    fun <A> attempt(a: Kind<F, A>): Kind<F, Either<Throwable, A>>
    fun <A> fail(t: Throwable): Kind<F, A>
}
//end::init[]
