package chapter1.sec1

val listing1 = {
    class CreditCard {
        fun charge(price: Float): Unit = TODO()
    }

    data class Coffee(val price: Float = 2.50F)

    //tag::init1[]
    class Cafe {

        fun buyCoffee(cc: CreditCard): Coffee {

            // instantiate a new cup of Coffee
            val cup = Coffee() // <1>

            // Charge credit card with the coffee's price. A side-effect
            cc.charge(cup.price) // <2>

            // Return the Coffee
            return cup // <3>
        }
    }
    //end::init1[]
}

val listing2 = {
    data class Coffee(val price: Float = 2.95F)

    class CreditCard

    class Payments {
        fun charge(cc: CreditCard, price: Float): Unit = TODO()
    }

    //tag::init2[]
    class Cafe {
        fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
            val cup = Coffee()
            p.charge(cc, cup.price)
            return cup
        }
    }
    //end::init2[]
}

val listing3 = {

    class CreditCard

    data class Coffee(val price: Float = 2.50F)

    data class Charge(val cc: CreditCard, val amount: Float)

    //tag::init3[]
    class Cafe {
        fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
            val cup = Coffee()
            return Pair(cup, Charge(cc, cup.price))
        }
    }
    //end::init3[]
}

val listing4 = {
    class CreditCard

    //tag::init4[]
    data class Charge(val cc: CreditCard, val amount: Float) { // <1>

        // A combine method combining charges for the same credit card
        fun combine(other: Charge): Charge = // <2>
            // Ensure it's the same card, otherwise throw an exception
            if (cc == other.cc) // <3>
                // A new Charge is returned, combining the amount of this
                //    and the other
                Charge(cc, amount + other.amount) // <4>
            else throw Exception(
                "Cannot combine charges to different cards"
            )
    }
    //end::init4[]
}

val listing5 = {

    class CreditCard

    data class Coffee(val price: Float = 2.50F)

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = TODO()
    }

    //tag::init5[]
    class Cafe {

        fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> = TODO()

        fun buyCoffees(
            cc: CreditCard,
            n: Int
        ): Pair<List<Coffee>, Charge> {

            val purchases: List<Pair<Coffee, Charge>> =
                // Create a self-initialized List.
                List(n) { buyCoffee(cc) } // <1>

            // Split the list of Pairs into two separate lists.
            val (coffees, charges) = purchases.unzip() // <2>

            // Produce the output pairing coffees to a combined single
            // charge
            return Pair(
                coffees,
                charges.reduce { c1, c2 -> c1.combine(c2) }
            ) // <3>
        }
    }
    //end::init5[]
}

val listing6 = {

    class CreditCard

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = TODO()
    }

    //tag::init6[]
    fun List<Charge>.coalesce(): List<Charge> =
        this.groupBy { it.cc }.values
            .map { it.reduce { a, b -> a.combine(b) } }
    //end::init6[]
}
