package com.example.notesappfrontend

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * PUBLIC_INTERFACE
 * Main activity presenting the list of notes in a minimal modern UI, with a FAB for new notes.
 */
class MainActivity : AppCompatActivity(), NotesAdapter.OnNoteClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.notes_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(NoteManager.getNotes(), this)
        recyclerView.adapter = notesAdapter

        fab = findViewById(R.id.fab_add_note)
        fab.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        notesAdapter.updateNotes(NoteManager.getNotes())
    }

    // PUBLIC_INTERFACE
    override fun onNoteClick(noteId: Long) {
        val intent = Intent(this, NoteDetailsActivity::class.java)
        intent.putExtra("note_id", noteId)
        startActivity(intent)
    }
}
