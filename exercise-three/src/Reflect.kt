class HypotheticalAssignment(var field1: Any?,
                             val field2: Any?,
                             val field3: List<Integer>?,
                             val field4: Any?) {
    // @JvmField val field5: Any? = "w" // causes field count and field privacy tests to fail
}