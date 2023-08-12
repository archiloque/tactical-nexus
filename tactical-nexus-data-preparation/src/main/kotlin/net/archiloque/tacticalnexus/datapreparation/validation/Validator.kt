package net.archiloque.tacticalnexus.datapreparation.validation

class Validator {

    companion object {
        fun checkDuplicates(elements: Map<Any, List<Any>>) {
            elements.forEach {
                    (t, u),
                ->
                if (u.size > 1) {
                    throw RuntimeException("Duplicated element [${t}]")
                }
            }
        }
    }
}