package net.decenternet.technicalexam.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.decenternet.technicalexam.BR
import net.decenternet.technicalexam.R
import net.decenternet.technicalexam.databinding.AdapterTasksBinding
import net.decenternet.technicalexam.domain.Task

class TasksAdapter(private var data: List<Task>, private var listener: TasksAdapterListener) : RecyclerView.Adapter<TasksAdapter.TasksAdapterViewHolder>() {

    interface TasksAdapterListener {
        fun onTaskItemClick(task: Task)
        fun onTaskDeleteClick(task: Task)
        fun onTaskCheckedChange(task: Task, checked: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapterViewHolder {
        val binding: AdapterTasksBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_tasks,
            parent,
            false
        )
        return TasksAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksAdapterViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener { listener.onTaskItemClick(data[position]) }
        holder.binding.delete.setOnClickListener { listener.onTaskDeleteClick(data[position]) }
        holder.binding.description.setOnCheckedChangeListener { view, isChecked ->
            listener.onTaskCheckedChange(data[position], isChecked)
        }
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<Task>) {
        this.data = data
        notifyDataSetChanged()
    }

    class TasksAdapterViewHolder(val binding: AdapterTasksBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.setVariable(BR.task, task)
            binding.executePendingBindings()
        }
    }
}