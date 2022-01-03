package net.decenternet.technicalexam.ui.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.decenternet.technicalexam.domain.Task
import net.decenternet.technicalexam.ui.tasks.TasksContract.Presenter

class TasksActivity : AppCompatActivity(), TasksContract.View {

    private val presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Finish this simple task recording app.
         * The following are the remaining todos for this project:
         * 1. Make sure all defects are fixed.
         * 2. Showing a dialog to add/edit the task.
         * 3. Allowing the user to toggle it as completed.
         * 4. Allowing the user to delete a task.
         *
         */
    }

    override fun displayTasks(tasks: List<Task?>?) {
        TODO("Not yet implemented")
    }

    override fun addTaskToList(task: Task?) {
        TODO("Not yet implemented")
    }

    override fun removeTaskFromList(task: Task?) {
        TODO("Not yet implemented")
    }

    override fun updateTaskInList(task: Task?) {
        TODO("Not yet implemented")
    }

    override fun popupTaskAddingDialog() {
        TODO("Not yet implemented")
    }

    override fun popupTaskEditorDialog(task: Task?) {
        TODO("Not yet implemented")
    }
}