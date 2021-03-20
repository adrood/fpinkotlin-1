package chapter2

object Listing_2_3 {

    //tag::init[]
    object Example {

        private fun abs(n: Int): Int =
            if (n < 0) -n
            else n

        // Add the factorial function, making it private
        private fun factorial(i: Int): Int { //<1>
            tailrec fun go(n: Int, acc: Int): Int =
                if (n <= 0) acc
                else go(n - 1, n * acc)
            return go(i, 1)
        }

        fun formatAbs(x: Int): String {
            val msg = "The absolute value of %d is %d"
            return msg.format(x, abs(x))
        }

        // Add the formatFactorial function, public by default
        fun formatFactorial(x: Int): String { //<2>
            val msg = "The factorial of %d is %d"
            return msg.format(x, factorial(x))
        }
    }

    fun main() {
        println(Example.formatAbs(-42))
        // Call formatFactorial from the main function
        println(Example.formatFactorial(7)) //<3>
    }
    //end::init[]
}
