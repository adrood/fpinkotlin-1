package chapter15.exercises.ex9

// import chapter15.sec2.lift
// import chapter15.sec2.processFile
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import java.io.File

//tag::init1[]
fun toCelsius(fahrenheit: Double): Double = TODO()
//end::init1[]

//tag::init2[]
fun convert(infile: File, outfile: File): File = TODO()
//end::init2[]

class Exercise9 : WordSpec({
    "convert" should {
        "!apply a process and write a new file" {
            val infile = File("src/test/resources/samples/preprocess.txt")
            val outfile = File("build/postprocess.txt")
            hasContent(convert(infile, outfile),
                """
                    30.0
                    20.0
                    10.0
                """.trimIndent()
            )
        }
    }
})

private fun hasContent(f: File, content: String) {
    f.bufferedReader().use {
        it.lines().toArray().joinToString("\n") shouldBe content
    }
}
