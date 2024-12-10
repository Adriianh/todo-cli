package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument

class FileCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Configurate your tasks file."

    private val path by argument(help = "File path to save your tasks")

    override fun run() {
        manager.setStoragePath(path)
        echo("Storage path updated to: $path")
    }
}