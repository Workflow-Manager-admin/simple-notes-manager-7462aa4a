package com.example.notesappfrontend

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * PUBLIC_INTERFACE
 * Activity to display the details for a note, and provide Edit/Delete options.
 */
class NoteDetailsActivity : AppCompatActivity() {
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val noteId = intent.getLongExtra("note_id", -1)
        note = NoteManager.getNoteById(noteId)

        val tvTitle = findViewById<TextView>(R.id.detail_title)
        val tvContent = findViewById<TextView>(R.id.detail_content)
        val fabEdit = findViewById<FloatingActionButton>(R.id.fab_edit_note)
        val fabDelete = findViewById<FloatingActionButton>(R.id.fab_delete_note)

        note?.let {
            tvTitle.text = it.title
            tvContent.text = it.content
        }

        fabEdit.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra("note_id", noteId)
            startActivity(intent)
        }

        fabDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Note?")
                .setMessage("This cannot be undone. Are you sure?")
                .setPositiveButton("Delete") { _: DialogInterface, _: Int ->
                    NoteManager.deleteNote(noteId)
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh note content in case of edit
        note?.id?.let { id ->
            note = NoteManager.getNoteById(id)
            findViewById<TextView>(R.id.detail_title).text = note?.title ?: ""
            findViewById<TextView>(R.id.detail_content).text = note?.content ?: ""
        }
    }
}
