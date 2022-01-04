package net.decenternet.technicalexam.ui.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.decenternet.technicalexam.R
import net.decenternet.technicalexam.data.TaskLocalServiceProvider
import net.decenternet.technicalexam.databinding.ActivityTasksBinding
import net.decenternet.technicalexam.domain.Task
import net.decenternet.technicalexam.ui.tasks.TasksContract.Presenter


class TasksActivity : AppCompatActivity(), TasksContract.View {

    private val taskLocalServiceProvider by lazy {
        val preference = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        TaskLocalServiceProvider(preference)
    }

    private lateinit var binding: ActivityTasksBinding
    private lateinit var presenter: Presenter
    private lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)
        /**
         * Finish this simple task recording app.
         * The following are the remaining todos for this project:
         * 1. Make sure all defects are fixed.
         * 2. Showing a dialog to add/edit the task.
         * 3. Allowing the user to toggle it as completed.
         * 4. Allowing the user to delete a task.
         *
         */
        presenter = TasksPresenter(this, taskLocalServiceProvider)
        adapter = TasksAdapter(emptyList(), object: TasksAdapter.TasksAdapterListener {
            override fun onTaskItemClick(task: Task) {
                popupTaskEditorDialog(task)
            }

            override fun onTaskDeleteClick(task: Task) {
                removeTaskFromList(task)
            }

            override fun onTaskCheckedChange(task: Task, checked: Boolean) {
                if (checked) presenter.onTaskChecked(task.id) else presenter.onTaskUnchecked(task.id)
            }

        })
        binding.recyclerviewTasks.adapter = adapter

        binding.fab.setOnClickListener { popupTaskAddingDialog() }

        presenter.getAllTasks()
    }

    override fun displayTasks(tasks: List<Task>) {
        adapter.updateData(tasks)
    }

    override fun addTaskToList(task: Task) {
        presenter.onAddTaskClicked(task)
    }

    override fun removeTaskFromList(task: Task) {
        presenter.onDeleteTaskClicked(task.id)
    }

    override fun updateTaskInList(task: Task) {
        presenter.onSaveTaskClicked(task)
    }

    override fun popupTaskAddingDialog() {
        openTaskInputDialog(null, "Add", "Cancel")
    }

    override fun popupTaskEditorDialog(task: Task) {
        openTaskInputDialog(task, "Save", "Cancel")
    }

    private fun openTaskInputDialog(
        task: Task? = null,
        textPositiveButton: String = "Yes",
        textNegativeButton: String = "No"
    ) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Add Task")
        val viewInflated: View = LayoutInflater.from(this).inflate(R.layout.dialog_edit_task, null, false)
        val input = viewInflated.findViewById<EditText>(R.id.edittext_description)
        builder.setView(viewInflated)

        task?.let {
            builder.setTitle("Edit Task")
            input.setText(it.description)
        }

        builder.setPositiveButton(textPositiveButton) { dialog, id ->
            if (task == null) {
                val newTask = Task(description = input.text.toString().trim(), completed = false)
                addTaskToList(newTask)
            } else {
                val updatedTask = Task(task.id, input.text.toString().trim(), task.completed)
                updateTaskInList(updatedTask)
            }
        }
        builder.setNegativeButton(textNegativeButton) { dialog, id ->
        }

        val dialog = builder.create()
        dialog.show()
    }
}