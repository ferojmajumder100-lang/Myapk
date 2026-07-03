package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.Note
import com.example.data.Task
import com.example.data.ZenRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ZenViewModel(private val repository: ZenRepository) : ViewModel() {

    // Expose Tasks stream as StateFlow
    val tasksState: StateFlow<List<Task>> = repository.allTasks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Expose Notes stream as StateFlow
    val notesState: StateFlow<List<Note>> = repository.allNotes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(title: String) {
        if (title.isNotBlank()) {
            viewModelScope.launch {
                repository.insertTask(Task(title = title.trim()))
            }
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTaskById(task.id)
        }
    }

    fun addNote(title: String, content: String, colorHex: String) {
        if (title.isNotBlank() || content.isNotBlank()) {
            viewModelScope.launch {
                repository.insertNote(
                    Note(
                        title = title.trim(),
                        content = content.trim(),
                        colorHex = colorHex
                    )
                )
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNoteById(note.id)
        }
    }
}

/**
 * Factory class to instantiate ZenViewModel with the ZenRepository parameter.
 */
class ZenViewModelFactory(private val repository: ZenRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ZenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
