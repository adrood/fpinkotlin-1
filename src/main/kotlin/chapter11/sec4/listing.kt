package chapter11.sec4

import arrow.Kind
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import chapter11.Gen

//tag::init1[]

// Listing 11.9.
// Declaration for an Item and Order text fixture

data class Order(val item: Item, val quantity: Int)
data class Item(val name: String, val price: Double)

val genOrder: Gen<Order> =
    // Generate a random string name
    Gen.string().flatMap { name: String -> // <1>
        // Generate a double price between 0 and 10
        Gen.double(0..10).flatMap { price: Double -> // <2>
            // Generate an integer quantity between 1 and 100
            Gen.choose(1, 100).map { quantity: Int -> // <3>
                Order(Item(name, price), quantity)
            }
        }
    }
//end::init1[]

//tag::init2[]
val genItem: Gen<Item> =
    Gen.string().flatMap { name: String ->
        Gen.double(0..10).map { price: Double ->
            Item(name, price)
        }
    }
//end::init2[]

//tag::init3[]
val genOrder2: Gen<Order> =
    Gen.choose(1, 100).flatMap { quantity: Int ->
        genItem.map { item: Item ->
            Order(item, quantity)
        }
    }
//end::init3[]

//tag::init4[]
val genOrder3: Gen<Order> =
    Gen.choose(1, 100).flatMap { quantity: Int ->
        Gen.string().flatMap { name: String ->
            Gen.double(0..10).map { price: Double ->
                Order(Item(name, price), quantity)
            }
        }
    }
//end::init4[]

val listing1 = {
    val x = Gen.unit<Int>(1)
    val f: (Int) -> Gen<Int> = { i -> Gen.unit(i) }
    val g: (Int) -> Gen<Int> = { i -> Gen.unit(i) }

    //tag::init5[]

    // Listing 11.10.
    // Expressing the law of associativity in terms of flatMap
    x.flatMap(f).flatMap(g) ==
        x.flatMap { a -> f(a).flatMap(g) }
    //end::init5[]
}

// Let's prove that the associative law holds for 'Option'.

val f: (Int) -> Option<Int> = { i -> Some(i) }
val g: (Int) -> Option<Int> = { i -> Some(i) }
val h: (Int) -> Option<Int> = { i -> Some(i) }

// substitute 'None' for 'x'

val listing2 = {
    //tag::init6[]
    None.flatMap(f).flatMap(g) ==
        None.flatMap { a -> f(a).flatMap(g) }
    //end::init6[]
}

// None.flatMap(f) = None for all f

val listing3 = {
    //tag::init7[]
    None == None
    //end::init7[]
}

val listing4 = {
    val v = 1
    val x = Some(v)

    //tag::init8[]

    // Listing 11.11.
    // Verifying the associative law by substitution of x by Some(v)

    // Original law of associativity for flatMap
    x.flatMap(f).flatMap(g) == x.flatMap { a -> f(a).flatMap(g) } // <1>

    // Substitute x with Some(v) on both sides
    Some(v).flatMap(f).flatMap(g) ==
        Some(v).flatMap { a -> f(a).flatMap(g) } // <2>

    // Collapse Some(v).flatMap on both sides by applying f to v
    // directly
    f(v).flatMap(g) == { a: Int -> f(a).flatMap(g) }(v) // <3>

    // Apply v to g directly on the right side, proving equality
    f(v).flatMap(g) == f(v).flatMap(g) // <4>
    //end::init8[]
}

interface Monad<F> {

    //tag::init21[]
    fun <A> unit(a: A): Kind<F, A>
    //end::init21[]

    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>

    //tag::init9[]
    fun <A, B, C> compose(
        f: (A) -> Kind<F, B>,
        g: (B) -> Kind<F, C>
    ): (A) -> Kind<F, C>
    //end::init9[]
}

interface Listing<F, A> : Monad<F> {

    val f: (A) -> Kind<F, A>
    val g: (A) -> Kind<F, A>
    val h: (A) -> Kind<F, A>
    val x: Kind<F, A>

    fun listing() {
        //tag::init10[]
        // Listing 11.12.
        // Expressing the law of associativity in terms of compose
        compose(compose(f, g), h) == compose(f, compose(g, h))
        //end::init10[]

        // Listing 11.13.
        // Apply the substitution model to left side of associative
        // law in terms of compose

        //left side
        val left1 =
            //tag::init11[]
            // Left side of law of associativity expressed in
            // terms of compose
            compose(compose(f, g), h) // <1>
        //end::init11[]

        val left2: (A) -> Kind<F, A> =
            //tag::init12[]
            // Substitute outer compose with flatMap, propagating a
            { a -> flatMap(compose(f, g)(a), h) } // <2>
        //end::init12[]

        val left3: (A) -> Kind<F, A> =
            //tag::init13[]
            // Substitute inner compose with flatMap, propagating b
            { a -> flatMap({ b: A -> flatMap(f(b), g) }(a), h) } // <3>
        //end::init13[]

        val left4: (A) -> Kind<F, A> =
            //tag::init14[]
            // Apply a through b to f
            { a -> flatMap(flatMap(f(a), g), h) } // <4>
        //end::init14[]

        val left5 =
            //tag::init15[]
            // Simplify by introducing alias x for any f with a applied
            flatMap(flatMap(x, g), h) // <5>
        //end::init15[]

        // Listing 11.14.
        // Apply substitution model to right side of associative law in
        // terms of compose

        //right side
        val right1 =
            //tag::init16[]
            // Right side of law of associativity expressed in
            // terms of compose.
            compose(f, compose(g, h)) // <1>
        //end::init16[]

        val right2: (A) -> Kind<F, A> =
            //tag::init17[]
            // Substitute outer compose with flatMap, propagating a
            { a -> flatMap(f(a), compose(g, h)) } // <2>
        //end::init17[]

        val right3: (A) -> Kind<F, A> =
            //tag::init18[]
            // Substitute inner compose with flatMap, propagating b
            { a -> flatMap(f(a)) { b -> flatMap(g(b), h) } } // <3>
        //end::init18[]

        val right4: Kind<F, A> =
            //tag::init19[]
            // Simplify by introducing alias x for any f with a applied
            flatMap(x) { b -> flatMap(g(b), h) } // <4>
        //end::init19[]

        //tag::init20[]
        flatMap(flatMap(x, g), h) ==
            flatMap(x) { b -> flatMap(g(b), h) }
        //end::init20[]
    }
}

interface Listing2<F, A> : Monad<F> {

    val f: (A) -> Kind<F, A>
    val x: Kind<F, A>
    val a: A

    fun listing() {
        //tag::init22[]
        compose(f, { a: A -> unit(a) }) == f
        compose({ a: A -> unit(a) }, f) == f
        //end::init22[]

        //tag::init23[]
        flatMap(x) { a -> unit(a) } == x
        flatMap(unit(a), f) == f(a)
        //end::init23[]
    }
}

// Exercises 11.6 - 11.14