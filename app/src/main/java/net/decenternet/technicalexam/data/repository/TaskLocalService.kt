package net.decenternet.technicalexam.data.repository

import net.decenternet.technicalexam.domain.Task

interface TaskLocalService {
    fun save(task: Task)
    fun findAll(): List<Task>
    fun delete(taskId: Int)
    fun update(task: Task)
    fun updateCompletion(taskId: Int, completed: Boolean)
}