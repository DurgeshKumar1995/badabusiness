package com.badabusiness.demo.ui.note_list.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.badabusiness.demo.databinding.ActivityMainBinding
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.ui.note_curd.view.NoteDetailsActivity
import com.badabusiness.demo.ui.note_list.adapter.NoteAdapter
import com.badabusiness.demo.ui.note_list.adapter.NoteLoadStateAdapter
import com.badabusiness.demo.ui.note_list.view_model.MainViewModel
import com.badabusiness.demo.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NoteAdapter { actionOnItemClick(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = NoteLoadStateAdapter(),
            header = NoteLoadStateAdapter()
        )

        lifecycleScope.launch {
            viewModel.data.collectLatest {
                adapter.submitData(it)
            }
        }

        viewModel.refreshPoint.observe(this) {
            it?.run {
                adapter.refresh()
            }
        }
        binding.btnAdd.setOnClickListener {
            actionOnItemClick()
        }

        adapter.addLoadStateListener { loadState ->

            if (loadState.append.endOfPaginationReached) {
                handleVisibility(adapter.itemCount < 1)
            }
        }

    }

    private fun actionOnItemClick(note: Note? = null) {

        val intent = Intent(applicationContext, NoteDetailsActivity::class.java)
        intent.putExtra(Constants.NOTE_SHARE_KEY, note)
        startForResult.launch(intent)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                adapter.refresh()
            }
        }

    private fun handleVisibility(isEmpty:Boolean){
        binding.recyclerView.isVisible = !isEmpty
        binding.txtEmpty.isVisible = isEmpty
    }
}