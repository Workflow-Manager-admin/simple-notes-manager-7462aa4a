package com.example.notesappfrontend

/**
 * PUBLIC_INTERFACE
 * Singleton object to manage the list of notes in memory.
 * Provides create, update, delete, and get operations.
 */
object NoteManager {
    private val notes = mutableListOf<Note>()

    fun getNotes(): List<Note> = notes.sortedByDescending { it.updatedAt }

    fun getNoteById(id: Long): Note? = notes.find { it.id == id }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun updateNote(note: Note) {
        val idx = notes.indexOfFirst { it.id == note.id }
        if (idx >= 0) {
            notes[idx] = note.copy(updatedAt = java.util.Date())
        }
    }

    fun deleteNote(id: Long) {
        notes.removeAll { it.id == id }
    }
}
