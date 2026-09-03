package com.exe.engine

/** Receives asynchronous results and lifecycle notifications from a document SDK. */
interface DocumentCallback<Result : Any, Event : Any> {
    fun onResult(result: Result)

    fun onEventReceived(event: Event) = Unit

    fun onSdkClosed() = Unit
}

/**
 * An implementation capable of asynchronously processing one kind of document.
 *
 * [Input] and [Result] belong to the document SDK, allowing check-ui, ID, and
 * future SDKs to expose models specific to their own workflows.
 */
interface DocumentEngine<Input : Any, Result : Any, Event : Any> {
    val documentType: DocumentType

    fun process(model: Input, callback: DocumentCallback<Result, Event>)
}
