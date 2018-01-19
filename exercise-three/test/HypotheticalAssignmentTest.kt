import org.junit.Assert.*
import org.junit.Test
import java.lang.reflect.Method
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

    @Test
    fun testForAtLeast2PrivateHelperMethods() {
        val privateMethods = clazz.declaredMethods.filter { Modifier.isPrivate(it.modifiers) }
        assertTrue(privateMethods.size > 1)
    }

    @Test
    fun testForMethodsWithThrowClause() {
        val throwingMethods = clazz.declaredMethods.filter { it.exceptionTypes.isNotEmpty() }
        var message = constructMessage("The following method(s) have throw clauses:\n", throwingMethods)
        assertTrue(message.toString(), throwingMethods.isEmpty())
    }

    @Test
    fun testForMethodsReturningInt() {
        val methodsReturningInt = clazz.declaredMethods.filter { it.returnType.name == "int" }
        var message = constructMessage("The following method(s) return Int:\n", methodsReturningInt)
        assertTrue(message, methodsReturningInt.isEmpty())
    }

    private fun constructMessage(initialDescription: String, failingMethods: Collection<Method>): String {
        var message = StringBuilder(initialDescription)
        for (method in failingMethods) {
            message.append(method.name + "\n")
        }
        return message.toString()
    }

    @Test
    fun testForZeroArgumentConstructor() {
        val cons = clazz.constructors
        var foundZeroArgumentConstructor = false
        for (constructor in cons) {
            println(constructor)
            if (constructor.parameterCount == 0) {
                foundZeroArgumentConstructor = true
            }
        }
        assertTrue("No zero argument constructor found.", foundZeroArgumentConstructor)
    }
}