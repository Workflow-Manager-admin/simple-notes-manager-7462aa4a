package com.example.notesappfrontend

import java.util.*

/**
 * PUBLIC_INTERFACE
 * Data model for a Note in the app.
 */
data class Note(
    val id: Long = System.currentTimeMillis(),
    var title: String,
    var content: String,
    val createdAt: Date = Date(),
    var updatedAt: Date = Date()
)
