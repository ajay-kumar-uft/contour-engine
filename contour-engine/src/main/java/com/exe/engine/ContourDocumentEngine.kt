package com.exe.engine

import com.exe.engine.models.DocumentInputModel
import java.util.concurrent.ConcurrentHashMap

/** Registry and entry point for document-specific engines. */
object ContourDocumentEngine {

    private val engines = ConcurrentHashMap<DocumentType, DocumentEngine>()

    /**
     * Registers [engine] for its document type. A later registration for the
     * same type replaces the previous implementation.
     */
    @JvmStatic
    fun register(engine: DocumentEngine) {
        engines[engine.documentType] = engine
    }

    /** Returns whether the dependency for [documentType] has registered an engine. */
    @JvmStatic
    fun isRegistered(documentType: DocumentType): Boolean = engines.containsKey(documentType)

    /**
     * Starts document processing and returns immediately. The registered SDK
     * invokes [callback] when its asynchronous work completes.
     */
    @JvmStatic
    fun process(
        documentType: DocumentType,
        model: DocumentInputModel,
        callback: DocumentCallback
    ) {
        val engine = engines[documentType]
            ?: throw DocumentEngineNotRegisteredException(documentType)

        engine.process(model, callback)
    }
}
