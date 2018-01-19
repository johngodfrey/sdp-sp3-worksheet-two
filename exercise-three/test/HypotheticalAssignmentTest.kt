import org.junit.Assert.*
import org.junit.Test

class HypotheticalAssignmentTest {
    private var assignment = HypotheticalAssignment(null, null, null, null)

    @Test
    fun testNumberOfFields() {
        val clazz = assignment.javaClass
        assertTrue("Assignment has ${clazz.declaredFields.size} fields.", clazz.declaredFields.size < 5)
    }

}