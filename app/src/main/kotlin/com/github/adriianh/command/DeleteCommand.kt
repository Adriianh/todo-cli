package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int

class DeleteCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Delete your tasks."

    private val id by argument(help = "The ID of the task to delete").int()

    override fun run() {
        if (manager.deleteTask(id)) echo("Task $id deleted")
        else echo("Task with ID $id not found")
    }
}