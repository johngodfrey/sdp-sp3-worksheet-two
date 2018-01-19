class HypotheticalAssignment(var field1: Any?,
                             val field2: Any?,
                             var field3: Any? = null,//listOf("ghgh", "fjfj"),
                             val field4: Any?) {
    // @JvmField val field5: Any? = "w" // causes field count and field privacy tests to fail

    private fun helper1(): Boolean {
        return true
    }

    private fun helper2(): Boolean {
        return false
    }

    // uncomment the method below to fail tests for methods throwing exceptions and returning Int
/*    @Throws(Exception::class)
    fun standardMethod(): Int {
        return 1
    }*/
}