package com.exe.engine

import java.util.ServiceLoader
import kotlin.jvm.Throws

interface Engine {
    fun process()
}

object ContourEngine {

    private val engine: Engine by lazy {
        ServiceLoader.load(Engine::class.java)
            .firstOrNull()
            ?: error(
                "No Engine implementation found. Add either opencv-engine or mediapipe-engine dependency."
            )
    }

    @Throws(IllegalStateException::class)
    fun process() {
        engine.process()
    }
}