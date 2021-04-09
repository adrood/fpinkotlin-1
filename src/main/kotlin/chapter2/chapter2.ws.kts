import chapter2.sec1.main
import chapter2.sec2.findFirst

// import chapter2.sec1.Example.factorial

main()

// formatResult

fun Int.show(): String =
    "The value of this Int is $this"

1.show()

findFirst(arrayOf(7, 9, 13)){ i:Int -> i == 9 }
;
{ x: Int, y: Int -> x == y }