package com.dohyeok.basicstatecodelab

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel: ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks get() = _tasks


    fun remove(task: WellnessTask) {
        _tasks.remove(task)
    }

    fun changeTaskChecked(task: WellnessTask, checked: Boolean) {
        Log.d("dhk", "task: $task, checked: $checked")
        _tasks.find { task.id == it.id }?.let { it.checked = checked }

    }
}
fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task #$i", false) }
