package com.example.notesapp.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isNotEmpty
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.TodoDialogBinding
import com.example.notesapp.databinding.TodoItemBinding
import com.example.notesapp.model.local.entity.Todo
import com.example.notesapp.view.adapters.RVAdapter
import com.example.notesapp.viewmodel.TodoViewModel
import java.util.*

class TodoDialog : DialogFragment() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var binding: TodoDialogBinding
    private lateinit var todoBinding: TodoItemBinding
    private val todoItems = mutableListOf<Todo>()

    /*private val todoAdapter by lazy {
        RVAdapter(
            todoItems,
            { inflater, container, attach, vh ->
                TodoItemBinding.inflate(inflater, container, attach).apply {
                    todoBinding = this
                }.run { vh(root) }
            }
        ) {
            todoBinding.todoItem.setText(it.title)
            todoBinding.noteCheckBox.isChecked = it.isDone
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TodoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        addItemsToSpinner()
        val isDoneDefault = false
        binding.apply {
            doneIcon.setOnClickListener {
                if ("${todoItemEdit.text}".isNotEmpty()) {
                    with(
                        Todo(
                            "${todoItemEdit.text}",
                            0,
                            isDoneDefault
                        )
                    ) {
                        todoItems.add(this)
                        todoViewModel.addTodo(this)
                        notifySuccess()
                    }
                    todoItemEdit.setText("")
                }
            }
            cancelIcon.setOnClickListener {
                dialog?.dismiss()
            }
        }

       /* binding.todoReCyclerDashBoard.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }*/
    }

    private fun addItemsToSpinner() {
        val priorities = resources.getStringArray(R.array.Priority)
        val categories = resources.getStringArray(R.array.Category)
    }

    private fun notifySuccess() {
        val builder = AlertDialog.Builder(requireContext()).apply{
            setMessage("Item added")
            setIcon(R.drawable.baseline_notifications_24)
            .setCancelable(true)
        }
        val dialog = builder.create()
        dialog.setTitle("Notification")
        dialog.show()

        val timer = Timer()
        timer.schedule(object: TimerTask(){
            override fun run(){
                dialog.dismiss()
                timer.cancel()
            }
        },700)
    }
    override fun onResume() {
        super.onResume()
        customizeDialog()
    }
    private fun customizeDialog() {
        val params = dialog?.window?.attributes
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.apply{
            setBackgroundDrawableResource(R.drawable.round_corner)
            attributes = params
        }
    }
    override fun onStop() {
        super.onStop()
        customizeDialog()
    }
    private fun initializeViewModel() {
        todoViewModel = ViewModelProvider(requireActivity())[TodoViewModel::class.java]
    }
}