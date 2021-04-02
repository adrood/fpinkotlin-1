package chapter14.sec1

//tag::init1[]
fun quicksort(xs: List<Int>): List<Int> =
    if (xs.isEmpty()) xs else {
        val arr = xs.toIntArray()

        // swaps two elements in an array
        fun swap(x: Int, y: Int) { // <1>
            val tmp = arr[x]
            arr[x] = arr[y]
            arr[y] = tmp
        }

        // Partitions a portion of the array into elements
        // less than and greater than pivot respectively
        fun partition(n: Int, r: Int, pivot: Int): Int { // <2>
            val pivotVal = arr[pivot]
            swap(pivot, r)
            var j = n
            for (i in n until r) if (arr[i] < pivotVal) {
                swap(i, j)
                j += 1
            }
            swap(j, r)
            return j
        }

        // Sorts a portion of the array in place
        fun qs(n: Int, r: Int): Unit = if (n < r) { // <3>
            val pi = partition(n, r, n + (n - r) / 2)
            qs(n, pi - 1)
            qs(pi + 1, r)
        } else Unit

        qs(0, arr.size - 1)
        arr.toList()
    }
//end::init1[]
