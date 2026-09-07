package com.exe.engine

import com.exe.engine.models.DocumentEventModel
import com.exe.engine.models.DocumentInputModel
import com.exe.engine.models.DocumentResultModel

/** Receives asynchronous results and lifecycle notifications from a document SDK. */
interface DocumentCallback {
    fun onResult(result: DocumentResultModel)

    fun onEventReceived(event: DocumentEventModel) = Unit

    fun onSdkClosed() = Unit
}

/**
 * An implementation capable of asynchronously processing one kind of document.
 *
 * All document SDK modules use the shared input, result, and event models
 * exposed by contour-engine.
 */
interface DocumentEngine {
    val documentType: DocumentType

    fun process(model: DocumentInputModel, callback: DocumentCallback)
}
