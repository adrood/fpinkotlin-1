package chapter15.sec1

import arrow.core.extensions.sequence.foldable.exists
import chapter13.boilerplate.io.IO
import java.io.File

//tag::init1[]

// Listing 15.1.
// Counting number of lines in a file with classic imperative style

fun linesGt40k(fileName: String): IO<Boolean> = IO {
    val limit = 40000
    val src = File(fileName)
    // Convenience method to access the BufferedReader from java.io.File
    val br = src.bufferedReader() // <1>
    try {
        var count = 0
        // The lineSequence extension method provides a Sequence<String>
        val lines = br.lineSequence().iterator() // <2>
        // Use the hasNext() to see if more lines are available
        while (count <= limit && lines.hasNext()) { // <3>
            // Calling next() has the side-effect of advancing
            // the iterator
            lines.next() // <4>
            count += 1
        }
        count > limit
    } finally {
        br.close()
    }
}
//end::init1[]

val listing1 = {
    val file = File("some_file.txt")
    val br = file.bufferedReader()
    val lines = br.lineSequence()
    //tag::init2[]
    lines.withIndex().exists { it.index >= 40000 }
    //end::init2[]

    //tag::init3[]
    lines.filter { it.trim().isNotBlank() }
        .withIndex()
        .exists { it.index >= 40000 }
    //end::init3[]

    //tag::init4[]
    lines.filter { it.trim().isNotBlank() }
        .take(40000)
        .map { it.first() }
        .joinToString("")
        .indexOf("abracadabra")
    //end::init4[]
}

//tag::init5[]
fun lines(fileName: String): IO<Sequence<String>> =
    IO {
        val file = File(fileName)
        val br = file.bufferedReader()
        val end: String by lazy {
            br.close()
            System.lineSeparator()
        }

        sequence {
            yieldAll(br.lineSequence())
            yield(end)
        }
    }
//end::init5[]
