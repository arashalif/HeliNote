package com.arashaghelifar.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arashaghelifar.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface NoteDAO {

    @Query("SELECT * FROM note WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun getNotes(query: String): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity) : Long

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}