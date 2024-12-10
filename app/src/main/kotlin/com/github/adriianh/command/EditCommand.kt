package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int

class EditCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Edit your tasks description."

    private val id by argument(help = "The ID of the task to edit").int()
    private val add by option(help = "Add a new item to task's description")
    private val remove by option(help = "The index of an item to remove from task's description").int()

    override fun run() {
        val tasks = manager.listTasks()
        val task = tasks.find { it.id == id }

        if (task == null) {
            echo("Task with ID $id not found")
            return
        }

        if (add == null && remove == null) {
            echo("No action specified. You must be use -add or --remove to edit task description.")
            return
        }

        if (add != null) {
            task.description += add!!
            manager.saveTasks()
            echo("Added to description: $add")
        }

        if (remove != null) {
            if (remove in 1..task.description.size) {
                task.description.removeAt(remove!! - 1)
                manager.saveTasks()
                echo("Removed from description: $remove")
            } else echo("Invalid index: $remove")
        }
    }
}