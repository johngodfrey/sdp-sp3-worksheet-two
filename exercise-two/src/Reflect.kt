import java.lang.reflect.Constructor
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

fun main(args: Array<String>) {
    if (args.size < 5) {
        println("Requires a class name and a list of 4 arguments")
        System.exit(-1)
    }

    val clazz = Class.forName(args[0])
    val constructors = clazz.constructors
    val constructorsWith4StringParams = mutableListOf<Constructor<*>>()
    for (constructor in constructors) {
        if (constructor.parameterCount == 4) {
            var allParametersTakeStrings = true
            for (parameter in constructor.parameterTypes) {
                if (parameter.canonicalName != "java.lang.String") {
                    allParametersTakeStrings = false
                }
            }
            if (allParametersTakeStrings) {
                constructorsWith4StringParams.add(constructor)
            }
        }
    }

    if (constructorsWith4StringParams.isNotEmpty()) {
        val dynamicReflectionTest = constructorsWith4StringParams[0].newInstance(args[1], args[2], args[3], args[4])
        println("Object created dynamically from command line input is: $dynamicReflectionTest")
    } else {
        println("Couldn't find a constructor that takes 4 strings for ${clazz.canonicalName}")
    }

    val manualReflectionTest = ReflectionTest("one", "two", "three", "four")
    println("manualReflectionTest is $manualReflectionTest")
}