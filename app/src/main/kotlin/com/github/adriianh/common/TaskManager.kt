package com.github.adriianh.common

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class TaskManager(configFilePath: String = "config.txt") {
    private val json = Json { prettyPrint = true }
    private val configFile = File(configFilePath)

    private var storageFile: String = loadStoragePath()
    private val file: File
        get() = File(storageFile)

    private var tasks: MutableList<Task> = loadTasks()

    private fun loadStoragePath(): String {
        return if (configFile.exists()) {
            configFile.readText().trim()
        } else {
            "tasks.json"
        }
    }

    fun setStoragePath(newPath: String) {
        storageFile = newPath
        configFile.writeText(newPath)
        tasks = loadTasks()
    }

    private fun loadTasks(): MutableList<Task> {
        return if (file.exists()) {
            val content = file.readText()

            if (content.isNotBlank()) {
                json.decodeFromString(content)
            } else mutableListOf()
        } else mutableListOf()
    }

    fun saveTasks() = file.writeText(json.encodeToString(tasks))

    fun addTask(name: String, content: List<String>) {
        Task(
            id = generateId(),
            name = name,
            description = content.toMutableList()
        ).let { tasks.add(it) }

        saveTasks()
    }

    fun deleteTask(id: Int): Boolean {
        val removed = tasks.removeIf { it.id == id }

        if (removed) saveTasks()
        return removed
    }

    fun completeTask(id: Int): Boolean {
        val task = tasks.find { it.id == id }

        return if (task != null) {
            task.done = true
            saveTasks()
            true
        } else false
    }

    fun listTasks(): List<Task> = tasks

    private fun generateId(): Int {
        return if (tasks.isEmpty()) 1 else tasks.maxOf { it.id } + 1
    }
}