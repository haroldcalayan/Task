package net.decenternet.technicalexam.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.decenternet.technicalexam.domain.Task
import java.util.*

class TaskLocalServiceProvider : TaskLocalService {

    private var sharedPreferences: SharedPreferences? = null
    private var localTasks: Collection<Task>? = null

    fun TaskLocalServiceProvider(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
        localTasks = Gson()
            .fromJson(
                sharedPreferences.getString("tasks", "[]"),
                object : TypeToken<List<Task?>?>() {}.type
            )
    }
    override fun save(task: Task) {
        val tasks = ArrayList(localTasks)
        task.id = getNextId()
        tasks.add(task)

        val editor = sharedPreferences!!.edit()
        editor.putString("tasks", Gson().toJson(tasks))
        editor.apply()

        localTasks = tasks
    }

    override fun findAll() = ArrayList(localTasks)

    private fun getNextId() = localTasks?.size?.plus(1) ?: 1

}