package com.arashaghelifar.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arashaghelifar.repository.model.NoteDTO

@Entity(tableName = "note")
internal data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val reminder: Long?,
)

internal fun NoteEntity.mapToDTO() = NoteDTO(
    id = id,
    title = title,
    content = content,
    reminder = reminder,
)

internal fun NoteDTO.mapToEntity() = NoteEntity(
    title = title,
    content = content,
    reminder = reminder,
    id = id
)


