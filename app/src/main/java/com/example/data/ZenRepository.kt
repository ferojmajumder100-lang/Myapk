package com.example.data

import kotlinx.coroutines.flow.Flow

class ZenRepository(private val zenDao: ZenDao) {

    // --- Tasks Streams and Methods ---
    val allTasks: Flow<List<Task>> = zenDao.getAllTasks()

    suspend fun insertTask(task: Task) {
        zenDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        zenDao.updateTask(task)
    }

    suspend fun deleteTaskById(taskId: Int) {
        zenDao.deleteTaskById(taskId)
    }

    // --- Notes Streams and Methods ---
    val allNotes: Flow<List<Note>> = zenDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        zenDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        zenDao.updateNote(note)
    }

    suspend fun deleteNoteById(noteId: Int) {
        zenDao.deleteNoteById(noteId)
    }
}
