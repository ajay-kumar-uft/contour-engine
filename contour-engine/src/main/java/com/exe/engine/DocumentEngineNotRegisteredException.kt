package com.exe.engine

/** Thrown when the host requests a document type whose SDK is not registered. */
class DocumentEngineNotRegisteredException(
    val documentType: DocumentType
) : IllegalStateException(
    "No DocumentEngine implementation is registered for '${documentType.value}'. " +
        "Add the dependency for this document type and register its engine."
)
