import java.lang.reflect.Modifier

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Requires a class name as argument to program")
        System.exit(-1)
    }

    val clazz = Class.forName(args[0])
    val name = args[0]
    val modifiers = clazz.modifiers
    val isInterface = Modifier.isInterface(modifiers)
    val interfaceOrClass = if (isInterface) "an interface" else "a class"
    val mods = Modifier.toString(modifiers)
    val constructors = StringBuilder()
    for (cons in clazz.constructors) {
        constructors.append("\t")
        constructors.append(cons)
        constructors.append("\n")
    }
    val methods = StringBuilder()
    for (method in clazz.methods) {
        methods.append("\t")
        methods.append(method)
        methods.append("\n")
    }
    val fields = StringBuilder()
    for (field in clazz.fields) {
        fields.append("\t")
        fields.append(field)
        fields.append("\n")
    }
    println("\nYou entered $name which is $interfaceOrClass, and its modifiers are $mods.")
    val constructorString = if (clazz.constructors.isNotEmpty()) "\nIts constructors are:\n$constructors" else ""
    print(constructorString)
    val methodString = if (clazz.methods.isNotEmpty()) "\nIts methods are:\n$methods" else ""
    print(methodString)
    val fieldsString = if (clazz.fields.isNotEmpty()) "\nIts fields are:\n$fields" else ""
    println(fieldsString)
}
