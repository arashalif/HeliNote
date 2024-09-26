package com.arashaghelifar.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arashaghelifar.database.dao.NoteDAO
import com.arashaghelifar.database.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
internal abstract class NotesDatabase : RoomDatabase() {

    abstract val noteDAO: NoteDAO

    companion object {
        const val DATABASE_NAME = "helitech-notes-database"
    }
}