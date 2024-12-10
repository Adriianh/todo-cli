package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context

class ListCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "List all your tasks."

    override fun run() {
        val tasks = manager.listTasks()

        if (tasks.isEmpty()) {
            echo("You haven't added any task")
            return
        }

        tasks.forEach { task ->
            echo("")
            echo("ID: ${task.id}, Name: ${task.name}, Done: ${if (task.done) "Yes" else "No"}")
            if (task.description.isNotEmpty()) {
                echo("  Description:")
                task.description.forEachIndexed { index, item ->
                    echo("    ${index + 1}. $item")
                }
            }
            echo("")
        }
    }
}