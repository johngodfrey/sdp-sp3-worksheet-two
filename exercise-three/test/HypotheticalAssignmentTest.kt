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
            if (!Modifier.isPrivate(field.modifiers)) {
                allFieldsPrivate = false
            }
        }
        assertTrue(allFieldsPrivate)
    }

    @Test
    fun testForMutableListField() {
        var foundMutableListField = false
        for (field in clazz.declaredFields) {
            if (field.type == Class.forName("java.util.List")) {
                println("field type is ${field.type}")
                foundMutableListField = true
            }
        }
        assertFalse(foundMutableListField)

    }
}