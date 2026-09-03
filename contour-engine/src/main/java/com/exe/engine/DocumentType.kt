package com.exe.engine

/**
 * Identifies the kind of document handled by a [DocumentEngine].
 *
 * This is deliberately not an enum so document SDKs can introduce new types
 * without requiring a new version of contour-engine.
 */
data class DocumentType(val value: String) {

    init {
        require(value.isNotBlank()) { "Document type must not be blank." }
    }

    companion object {
        @JvmField
        val CHECK = DocumentType("check")

        @JvmField
        val ID = DocumentType("id")
    }
}
