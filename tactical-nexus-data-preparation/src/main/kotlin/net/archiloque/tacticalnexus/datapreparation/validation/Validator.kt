package net.archiloque.tacticalnexus.datapreparation.validation

abstract class Validator {

    var hadError: Boolean = false

    fun foundError(message: String) {
        System.err.println(message)
        hadError = true
    }

    fun checkDuplicates(elements: Map<Any, List<Any>>) {
        elements.forEach {
                (t, u),
            ->
            if (u.size > 1) {
                foundError("Duplicated element [${t}]")
            }
        }
    }
}