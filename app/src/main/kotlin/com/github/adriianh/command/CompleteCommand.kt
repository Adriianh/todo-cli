package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int

class CompleteCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Mark your tasks as completed."

    private val id by argument("The ID of the task to mark as completed").int()

    override fun run() {
        val task = manager.listTasks().find { it.id == id }

        if (task == null) {
            echo("Task with ID $id not found")
            return
        }

        if (manager.completeTask(id)) echo("Task with ID $id marked as completed")
        else echo("Task with ID $id cannot be marked as completed")
    }
}