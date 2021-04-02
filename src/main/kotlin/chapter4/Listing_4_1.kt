package chapter4

object Listing_4_1 {

    //tag::init[]
    fun failingFn(i: Int): Int {
        // Declaration of type Int throws Exception
        val y: Int = throw Exception("boom") // <1>
        return try {
            val x = 42 + 5
            x + y
        } catch (e: Exception) {
            // Unreachable code, so not returning 43
            43 // <2>
        }
    }
    //end::init[]

    //tag::init2[]
    fun failingFn2(i: Int): Int =
        try {
            val x = 42 + 5
            // A thrown Exception can be annotated with any type,
            // here it is Int
            x + (throw Exception("boom!")) as Int // <1>
        } catch (e: Exception) {
            // Exception is caught, so returning 43
            43 // <2>
        }
    //end::init2[]
}
