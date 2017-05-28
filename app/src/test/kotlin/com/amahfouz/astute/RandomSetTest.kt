package com.amahfouz.astute

import com.amahfouz.astute.model.RandomSet
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by amahfouz on 5/28/17.
 */
class RandomSetTest {

    @Test
    @Throws(Exception::class)
    fun set_isUnique() {
        val max = 9
        val rs = RandomSet(3, max)

        assertEquals(rs.numbers.size, 3)

        for (num in rs.numbers) {
            assert(num <= max)
        }

        // assert there are no duplicates
        for ((i1, first) in rs.numbers.withIndex())
            for ((i2, second) in rs.numbers.withIndex())
                if (i1 != i2)
                    assertNotEquals(first, second)
    }
}