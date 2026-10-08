package com.aml.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

class Converters {
    @TypeConverter
    fun fromStringList(value: String?): List<String> = value?.split(",")?.filter { it.isNotBlank() } ?: emptyList()

    @TypeConverter
    fun toStringList(value: List<String>): String = value.joinToString(",")
}

@Database(
    entities = [
        ProviderEntity::class,
        ModelEntity::class,
        ChatEntity::class,
        TurnEntity::class,
        MessageEntity::class,
        ThinkingEntity::class,
        AttachmentEntity::class,
        ToolCallEntity::class,
        MediaJobEntity::class,
        LocalModelEntity::class,
        DownloadEntity::class,
        PresetEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AmlDatabase : RoomDatabase() {
    // DAOs can be added in later milestones when the full schema is expanded.
}

// Minimal entities used to bootstrap the Room layer and M1 compatibility.
class ProviderEntity
class ModelEntity
class ChatEntity
class TurnEntity
class MessageEntity
class ThinkingEntity
class AttachmentEntity
class ToolCallEntity
class MediaJobEntity
class LocalModelEntity
class DownloadEntity
class PresetEntity
