class HypotheticalAssignment(var field1: Any?,
                             val field2: Any?,
                             var field3: Any? = null,//listOf("ghgh", "fjfj"),
                             val field4: Any?) {
    // @JvmField val field5: Any? = "w" // causes field count and field privacy tests to fail

    private fun helper1() {
        true
    }

    private fun helper2() {
        false
    }
}