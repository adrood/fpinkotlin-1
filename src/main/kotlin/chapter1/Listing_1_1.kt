package chapter1

/**
 * A Kotlin program with side effects
 */
object Listing_1_1 {
    class CreditCard {
        fun charge(price: Float): Unit = TODO()
    }

    data class Coffee(val price: Float = 2.50F)

    //tag::init[]
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
    //end::init[]
}
