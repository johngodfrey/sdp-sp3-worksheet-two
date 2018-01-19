import org.junit.Assert.*
import org.junit.Test
import java.lang.reflect.Modifier

class HypotheticalAssignmentTest {
    private var assignment = HypotheticalAssignment(null, null, null, null)
    private val clazz = assignment.javaClass

    @Test
    fun testNumberOfFields() {
        assertTrue("Assignment has ${clazz.declaredFields.size} fields.", clazz.declaredFields.size < 5)
    }

    @Test
    fun testForNonPrivateFields() {
        var allFieldsPrivate = true
        for (field in clazz.declaredFields) {
            println(Modifier.isPrivate(field.modifiers))
            println(field)
            if (!Modifier.isPrivate(field.modifiers)) {
                allFieldsPrivate = false
            }
        }
        assertTrue(allFieldsPrivate)
    }
}