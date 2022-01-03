package net.decenternet.technicalexam.ui.tasks

import net.decenternet.technicalexam.data.TaskLocalService
import net.decenternet.technicalexam.domain.Task

class TasksPresenter(taskLocalService: TaskLocalService?) : TasksContract.Presenter {

    override fun onAddTaskClicked() {
        TODO("Not yet implemented")
    }

    override fun onSaveTaskClicked(task: Task?) {
        TODO("Not yet implemented")
    }

    override fun onTaskChecked(taskId: Int) {
        TODO("Not yet implemented")
    }

    override fun onTaskUnchecked(taskId: Int) {
        TODO("Not yet implemented")
    }

    override fun onDeleteTaskClicked(taskId: Int) {
        TODO("Not yet implemented")
    }
}