package com.badabusiness.demo.ui.note_curd.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.badabusiness.demo.R
import com.badabusiness.demo.databinding.ActivityNoteDetailsBinding
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.ui.note_curd.view_model.NoteDetailsViewModel
import com.badabusiness.demo.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
            it?.let {isSuccess->
                if (isSuccess) {
                    setResult(RESULT_OK)
                    finish()
                }else{
                    Toast.makeText(baseContext, getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
                    enableButtons()
                }
            }
        }

        binding.run {

            btnCreate.setOnClickListener {
                it.isEnabled = false
                if (inputValidation()) {
                    val note = Note(
                        title = edtTitle.text.toString().trim(),
                        description = edtDescription.text.toString().trim(),
                        imageUrl = edtUrl.text.toString().trim()
                    )
                    viewModel.addData(note)
                }else{
                    it.isEnabled = true
                }
            }
            btnUpdate.setOnClickListener {
                it.isEnabled = false
                if (inputValidation()) {
                    this@NoteDetailsActivity.note?.also { note: Note ->
                        note.title = edtTitle.text.toString().trim()
                        note.description = edtDescription.text.toString().trim()
                        note.imageUrl = edtUrl.text.toString().trim()
                        note.editTag = true
                        note.updatedDate = Date()
                    }
                    viewModel.update(this@NoteDetailsActivity.note!!)

                }else{
                    it.isEnabled = true
                }
            }
            btnDelete.setOnClickListener {
                if(this@NoteDetailsActivity.note==null){
                    finish()
                    return@setOnClickListener
                }
                it.isEnabled = false
                viewModel.delete(this@NoteDetailsActivity.note!!)
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

    private fun inputValidation():Boolean{
        if (binding.edtTitle.text.toString().trim().length<3) {
            binding.tlTitle.error = getString(R.string.please_enter_title)
            return false
        }
        if (binding.edtDescription.text.toString().trim().length<3) {
            binding.tlTitle.error = getString(R.string.please_enter_description)
            return false
        }
        return true
    }

    private fun enableButtons(){
        binding.run {
            btnCreate.isEnabled = true
            btnUpdate.isEnabled = true
            btnDelete.isEnabled = true
        }
    }
}