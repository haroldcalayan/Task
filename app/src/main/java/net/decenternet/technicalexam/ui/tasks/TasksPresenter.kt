package net.decenternet.technicalexam.ui.tasks

import net.decenternet.technicalexam.data.TaskLocalServiceProvider
import net.decenternet.technicalexam.domain.Task

class TasksPresenter(val view: TasksContract.View, val taskLocalService: TaskLocalServiceProvider) : TasksContract.Presenter {

    override fun onAddTaskClicked(task: Task) {
        taskLocalService.save(task)
        getAllTasks()
    }

    override fun onSaveTaskClicked(task: Task) {
        taskLocalService.update(task)
        getAllTasks()
    }

    override fun onTaskChecked(taskId: Int) {
        taskLocalService.updateCompletion(taskId, true)
    }

    override fun onTaskUnchecked(taskId: Int) {
        taskLocalService.updateCompletion(taskId, false)
    }

    override fun onDeleteTaskClicked(taskId: Int) {
        taskLocalService.delete(taskId)
        getAllTasks()
    }

    override fun getAllTasks() {
        view.displayTasks(taskLocalService.findAll())
    }
}