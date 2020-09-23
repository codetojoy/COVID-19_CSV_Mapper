
package net.codetojoy.utils

import org.junit.*
import static org.junit.Assert.*

class UtilsTestCase {
    def utils = new Utils()

    @Test
    void testIsEqualSafe_basic() {
        // test
        def result = utils.isEqualSafe(" fooBAR", "FOObar ")

        assertEquals(true, result)
    }

    @Test
    void testIsEqualSafe_leftNull() {
        // test
        def result = utils.isEqualSafe(null, "FOObar ")

        assertEquals(false, result)
    }

    @Test
    void testIsEqualSafe_rightNull() {
        // test
        def result = utils.isEqualSafe(" fooBAR", null)

        assertEquals(false, result)
    }

    @Test
    void testBuildList_basic() {
        // test
        def result = utils.buildList(['a','b','c'])

        def expected = /"a","b","c"/

        assertEquals(expected, result)
    }

    @Test
    void testCleanTotal_case1() {
        // test
        def result = utils.cleanTotal('$500.00' as String)

        assertEquals((float) 500, result, 0.0f)
    }

    @Test
    void testCleanTotal_case2() {
        // test
        def result = utils.cleanTotal('"$1500.00"' as String)

        assertEquals((float) 1500, result, 0.0f)
    }
}
