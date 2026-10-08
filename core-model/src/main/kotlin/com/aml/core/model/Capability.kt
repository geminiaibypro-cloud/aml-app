package com.aml.core.model

enum class Capability {
    TEXT,
    VISION,
    REASONING,
    TOOLS,
    IMAGE_GEN,
    VIDEO_GEN,
    AUDIO_IN,
    AUDIO_OUT
}

@JvmInline
value class ModelKey(val value: String) {
    companion object {
        fun of(providerId: String, modelId: String): ModelKey = ModelKey("$providerId:$modelId")
    }
}

data class ModelInfo(
    val key: ModelKey,
    val displayName: String,
    val detected: Set<Capability> = setOf(Capability.TEXT),
    val overrides: Map<Capability, Boolean> = emptyMap(),
    val contextWindow: Int? = null,
    val maxOutputTokens: Int? = null,
    val pinned: Boolean = false,
) {
    val effective: Set<Capability>
        get() = (detected + overrides.filterValues { it }.keys) - overrides.filterValues { !it }.keys
}

data class Usage(
    val inputTokens: Int? = null,
    val outputTokens: Int? = null,
    val reasoningTokens: Int? = null,
    val cachedTokens: Int? = null,
)

sealed interface StreamEvent {
    data class Token(val text: String) : StreamEvent
    data class ThinkingToken(val text: String) : StreamEvent
    data class ToolCall(val id: String, val name: String, val argsJson: String) : StreamEvent
    data class Done(val usage: Usage? = null, val finishReason: String? = null) : StreamEvent
    data class Error(val error: AmlError) : StreamEvent
}

sealed interface AmlError {
    data object NoKey : AmlError
    data class Unauthorized(val detail: String?) : AmlError
    data class RateLimited(val retryAfterSec: Long?) : AmlError
    data class BadRequest(val detail: String?) : AmlError
    data class ContextTooLong(val detail: String?) : AmlError
    data class ModelNotFound(val detail: String?) : AmlError
    data class Server(val code: Int, val detail: String?) : AmlError
    data object Timeout : AmlError
    data object Offline : AmlError
    data class Cancelled(val byUser: Boolean) : AmlError
    data class EngineDied(val detail: String?) : AmlError
    data class OutOfMemory(val detail: String?) : AmlError
    data class Unsupported(val what: String) : AmlError
    data class Unknown(val detail: String?) : AmlError
}
