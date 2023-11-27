package com.example.notestakingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDbModel: NoteDbModel)

    @Update
    suspend fun updateNote(noteDbModel: NoteDbModel)

    @Delete
    suspend fun deleteNote(noteDbModel: NoteDbModel)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteContent LIKE :query")
    fun searchNote(query: String?): LiveData<List<NoteDbModel>>
}