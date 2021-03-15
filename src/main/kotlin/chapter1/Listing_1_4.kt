package chapter1

/**
 * Charge as a data type
 */
object Listing_1_4 {
    class CreditCard

    //tag::init[]
    // A data class declaration with constructor and immutable fields
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
    //end::init[]
}
