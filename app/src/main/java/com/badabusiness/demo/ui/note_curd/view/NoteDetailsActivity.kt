package com.badabusiness.demo.ui.note_curd.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.badabusiness.demo.R
import com.badabusiness.demo.databinding.ActivityNoteDetailsBinding
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.ui.note_curd.view_model.NoteDetailsViewModel
import com.badabusiness.demo.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailsBinding

    private val viewModel by viewModel<NoteDetailsViewModel>()

    private var note: Note?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        setContentView(
            ActivityNoteDetailsBinding.inflate(layoutInflater).also { binding = it }.root
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra(Constants.NOTE_SHARE_KEY)) {
            note = if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(Constants.NOTE_SHARE_KEY, Note::class.java)
            } else {
                intent.getParcelableExtra(Constants.NOTE_SHARE_KEY)
            }
            binding.note = note
        }

        viewModel.refreshPoint.observe(this){
            if(it==true){
                setResult(RESULT_OK)
                finish()
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}