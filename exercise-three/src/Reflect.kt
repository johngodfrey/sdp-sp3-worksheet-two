class HypotheticalAssignment(public var field1: Any?, private val field2: Any?, val field3: Any?, val field4: Any?) {
    // @JvmField val field5: Any? = "w" // causes field count and field privacy tests to fail
}