package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Task Entity to store daily checklist items.
 */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Note Entity to store quick thoughts, ideas, or reminders.
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val colorHex: String, // Store visual card color
    val createdAt: Long = System.currentTimeMillis()
)
