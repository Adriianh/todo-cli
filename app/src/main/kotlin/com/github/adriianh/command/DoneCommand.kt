package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int

class DoneCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Mark your tasks as done."

    private val id by argument("The ID of the task to mark as done").int()

    override fun run() {
        val task = try {
            manager.findTaskOrError(id)
        } catch (e: IllegalArgumentException) {
            echo(e.message)
            return
        }

        if (manager.completeTask(task)) echo("Task with ID $id marked as done")
        else echo("Task with ID $id cannot be marked as done")
    }
}