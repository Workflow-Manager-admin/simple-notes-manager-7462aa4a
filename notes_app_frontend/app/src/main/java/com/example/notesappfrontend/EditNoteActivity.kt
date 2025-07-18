package com.example.notesappfrontend

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * PUBLIC_INTERFACE
 * Activity to create or edit notes.
 */
class EditNoteActivity : AppCompatActivity() {
    private var editingNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val edtTitle = findViewById<EditText>(R.id.edit_note_title)
        val edtContent = findViewById<EditText>(R.id.edit_note_content)
        val fabSave = findViewById<FloatingActionButton>(R.id.fab_save_note)

        val noteId = intent.getLongExtra("note_id", -1)
        if (noteId != -1L) {
            editingNote = NoteManager.getNoteById(noteId)
            editingNote?.let {
                edtTitle.setText(it.title)
                edtContent.setText(it.content)
            }
        }

        fabSave.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val content = edtContent.text.toString().trim()
            if (title.isEmpty() && content.isEmpty()) {
                Toast.makeText(this, "Cannot save empty note", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (editingNote != null) {
                editingNote!!.title = title
                editingNote!!.content = content
                NoteManager.updateNote(editingNote!!)
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
            } else {
                NoteManager.addNote(Note(title = title, content = content))
                Toast.makeText(this, "Note created", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
