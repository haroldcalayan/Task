package net.decenternet.technicalexam.ui.tasks

import net.decenternet.technicalexam.domain.Task

interface TasksContract {

    interface View {
        fun displayTasks(tasks: List<Task?>?)
        fun addTaskToList(task: Task?)
        fun removeTaskFromList(task: Task?)
        fun updateTaskInList(task: Task?)
        fun popupTaskAddingDialog()
        fun popupTaskEditorDialog(task: Task?)
    }

    interface Presenter {
        fun onAddTaskClicked()
        fun onSaveTaskClicked(task: Task?)
        fun onTaskChecked(taskId: Int)
        fun onTaskUnchecked(taskId: Int)
        fun onDeleteTaskClicked(taskId: Int)
    }
}