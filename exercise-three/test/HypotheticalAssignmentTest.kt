import org.junit.Assert.*
import org.junit.Test
import java.lang.reflect.Modifier

class HypotheticalAssignmentTest {
    private var assignment = HypotheticalAssignment(null, null, field4 = null)
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
        var mutableListFieldName = ""
        for (field in clazz.declaredFields) {
            field.trySetAccessible()
            if (field.get(assignment)?.javaClass == Class.forName("java.util.ArrayList")) {
                foundMutableListField = true
                mutableListFieldName = field.name
            }
        }
        assertFalse("Field $mutableListFieldName is a MutableList!", foundMutableListField)
    }
}