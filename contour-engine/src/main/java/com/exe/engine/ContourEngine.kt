package com.exe.engine

object ContourEngine {

    @Volatile
    private var engine: Engine? = null

    fun register(engine: Engine) {
        this.engine = engine
    }

    fun process() {
        val engine = engine ?: throw IllegalStateException(
            "No Engine implementation found. " +
                    "Add either sdk-opencv or sdk-live dependency."
        )

        engine.process()
    }
}