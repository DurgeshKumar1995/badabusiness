package com.badabusiness.demo.ui.note_list.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.badabusiness.demo.databinding.ActivityMainBinding
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.ui.note_list.adapter.NoteAdapter
import com.badabusiness.demo.ui.note_list.adapter.NoteLoadStateAdapter
import com.badabusiness.demo.ui.note_list.view_model.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NoteAdapter{actionOnItemClick(it)} }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding =it }.root)

        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = NoteLoadStateAdapter(),
            header = NoteLoadStateAdapter()
        )

        lifecycleScope.launch {
            viewModel.data.distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }

        viewModel.refreshPoint.observe(this){
            it?.run {
                adapter.refresh()
            }
        }
        binding.btnAdd.setOnClickListener{
            viewModel.addData()
        }

//       viewModel.addData()
    }

    private fun actionOnItemClick(note: Note?) {
        Toast.makeText(baseContext, "Hello", Toast.LENGTH_SHORT).show()
        if (note!=null) {
//        viewModel.delete(note)
            note.title = "${note.title} Update"
            viewModel.update(note)
        }
//        val intent = Intent(applicationContext,ImageDetailsActivity::class.java)
//        intent.putExtra(Constants.IMADE_DETAIL_KEY,photo)
//        startForResult.launch(intent)
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // Handle the Intent
        }
    }
}