package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.option

class AddCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Add a new task."

    private val taskName by argument(help = "Task's name")
    private val description: List<String>? by option(help = "A comma-separated list of task's description")
        .convert { it.split(",").map(String::trim) }

    override fun run() {
        manager.addTask(name = taskName, content = description ?: emptyList())
        echo("Task added: $taskName")

        if (description.isNullOrEmpty()) {
            echo("Description: ")
            description?.forEachIndexed { index, item ->
                echo("  ${index + 1}. $item")
            }
        }
    }
}