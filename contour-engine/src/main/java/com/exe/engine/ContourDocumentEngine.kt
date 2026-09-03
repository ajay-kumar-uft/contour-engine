package com.exe.engine

import java.util.concurrent.ConcurrentHashMap

/** Registry and entry point for document-specific engines. */
object ContourDocumentEngine {

    private val engines = ConcurrentHashMap<DocumentType, DocumentEngine<*, *, *>>()

    /**
     * Registers [engine] for its document type. A later registration for the
     * same type replaces the previous implementation.
     */
    @JvmStatic
    fun register(engine: DocumentEngine<*, *, *>) {
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
    fun <Input : Any, Result : Any, Event : Any> process(
        documentType: DocumentType,
        model: Input,
        callback: DocumentCallback<Result, Event>
    ) {
        val engine = engines[documentType]
            ?: throw DocumentEngineNotRegisteredException(documentType)

        invoke(engine, model, callback)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <Input : Any, Result : Any, Event : Any> invoke(
        engine: DocumentEngine<*, *, *>,
        model: Input,
        callback: DocumentCallback<Result, Event>
    ) {
        (engine as DocumentEngine<Input, Result, Event>).process(model, callback)
    }
}
