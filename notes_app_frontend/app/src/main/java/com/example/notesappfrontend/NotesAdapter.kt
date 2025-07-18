package com.example.notesappfrontend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for displaying a list of notes.
 */
class NotesAdapter(
    private var notes: List<Note>,
    private val listener: OnNoteClickListener
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    interface OnNoteClickListener {
        fun onNoteClick(noteId: Long)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.item_note_title)
        val tvContent: TextView = itemView.findViewById(R.id.item_note_preview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvTitle.text = note.title
        holder.tvContent.text = if (note.content.length > 100) note.content.substring(0, 100) + "…" else note.content
        holder.itemView.setOnClickListener { listener.onNoteClick(note.id) }
    }

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
