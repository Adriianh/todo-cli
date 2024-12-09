package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class FileCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "Configurate your tasks file."

    private val path by option(help = "File path to save your tasks").required()

    override fun run() {
        manager.setStoragePath(path)
        echo("Storage path updated to: $path")
    }
}