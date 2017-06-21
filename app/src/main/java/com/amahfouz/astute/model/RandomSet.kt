package com.amahfouz.astute.model

/**
 * Random set of n unique integers between 0 and max
 */
class RandomSet(val size: Int, max: Int) : Iterable<Int>{

    private val numbers = IntArray(size)

    init {
        var rand: Int;
        var count = 0

        // generate exactly 'count' numbers discarding duplicates
        while (count < size) {
            rand = Math.floor(Math.random() * (max + 1)).toInt()
            if (rand !in numbers) {
                numbers[count] = rand
                count++
            }
        }
    }

    fun contains(element: Int) = numbers.contains(element)

    //
    // Iterable implementation
    //

    override fun iterator(): Iterator<Int> {
        return numbers.iterator()
    }

}