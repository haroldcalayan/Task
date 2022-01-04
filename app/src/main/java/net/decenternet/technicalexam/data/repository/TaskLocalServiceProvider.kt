package net.decenternet.technicalexam.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.decenternet.technicalexam.domain.Task

class TaskLocalServiceProvider : TaskLocalService {

    private var sharedPreferences: SharedPreferences? = null
    private var localTasks: ArrayList<Task>? = null

    constructor(preference: SharedPreferences) {
        this.sharedPreferences = preference
        localTasks = Gson()
            .fromJson(
                sharedPreferences?.getString("tasks", "[]"),
                object : TypeToken<List<Task?>?>() {}.type
            )
    }

    override fun save(task: Task) {
        val tasks = ArrayList(localTasks)
        task.id = getNextId()
        tasks.add(task)

        updateDatabase(tasks)
        localTasks = tasks
    }

    override fun findAll() = ArrayList(localTasks)

    override fun delete(taskId: Int) {
        localTasks?.remove(localTasks?.findLast { it.id == taskId })
        updateDatabase(localTasks.orEmpty())
    }

    override fun update(task: Task) {
        localTasks?.findLast { it.id == task.id }?.apply {
            description = task.description
            completed = task.completed
        }
        updateDatabase(localTasks.orEmpty())
    }

    override fun updateCompletion(taskId: Int, newCompletion: Boolean) {
        localTasks?.findLast { it.id == taskId }?.apply {
            completed = newCompletion
        }
        updateDatabase(localTasks.orEmpty())
    }

    private fun getNextId() = if (localTasks.isNullOrEmpty()) 1 else localTasks?.last()?.id?.plus(1) ?: 1

    private fun updateDatabase(tasks: List<Task>) {
        val editor = sharedPreferences?.edit()
        editor?.putString("tasks", Gson().toJson(tasks))
        editor?.apply()
    }

    private fun loadInitialData() {
        val tasks = ArrayList<Task>()
        tasks.add(Task(1, "Task 1", false))
        tasks.add(Task(2, "Task 2", false))
        tasks.add(Task(3, "Task 3", false))
        tasks.add(Task(4, "Task 4", false))
        tasks.add(Task(5, "Task 5", false))
        tasks.add(Task(6, "Task 6", false))
        tasks.add(Task(7, "Task 7", false))
        tasks.add(Task(8, "Task 8", false))
        tasks.add(Task(9, "Task 9", false))
        tasks.add(Task(10, "Task 10", false))
        updateDatabase(tasks)
        localTasks = tasks
    }

}