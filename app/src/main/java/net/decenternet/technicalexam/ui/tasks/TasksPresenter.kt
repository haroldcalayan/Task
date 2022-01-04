package net.decenternet.technicalexam.ui.tasks

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.decenternet.technicalexam.data.repository.TaskLocalServiceProvider
import net.decenternet.technicalexam.domain.Task

class TasksPresenter(val view: TasksContract.View, val taskLocalService: TaskLocalServiceProvider) : TasksContract.Presenter {

    override fun onAddTaskClicked(task: Task) {
        GlobalScope.launch {
            taskLocalService.save(task)
            getAllTasks()
        }
    }

    override fun onSaveTaskClicked(task: Task) {
        GlobalScope.launch {
            taskLocalService.update(task)
            getAllTasks()
        }
    }

    override fun onTaskChecked(taskId: Int) {
        GlobalScope.launch {
            taskLocalService.updateCompletion(taskId, true)
        }
    }

    override fun onTaskUnchecked(taskId: Int) {
        GlobalScope.launch {
            taskLocalService.updateCompletion(taskId, false)
        }
    }

    override fun onDeleteTaskClicked(taskId: Int) {
        GlobalScope.launch {
            taskLocalService.delete(taskId)
            getAllTasks()
        }
    }

    override fun getAllTasks() {
        GlobalScope.launch {
            view.displayTasks(taskLocalService.findAll())
        }
    }
}