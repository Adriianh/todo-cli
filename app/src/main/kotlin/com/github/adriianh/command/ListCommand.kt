package com.github.adriianh.command

import com.github.adriianh.common.TaskManager
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.mordant.rendering.BorderType.Companion.SQUARE_DOUBLE_SECTION_SEPARATOR
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.Companion.rgb
import com.github.ajalt.mordant.rendering.TextStyles
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal

class ListCommand(private val manager: TaskManager) : CliktCommand() {
    override fun help(context: Context) = "List all your tasks."

    override fun run() {
        val tasks = manager.listTasks()

        if (tasks.isEmpty()) {
            echo("You haven't added any task")
            return
        }

        val table = table {
            borderType = SQUARE_DOUBLE_SECTION_SEPARATOR
            borderStyle = rgb("#4b25b9")
            align = TextAlign.RIGHT
            tableBorders = Borders.NONE

            header {
                style = TextColors.brightRed + TextStyles.bold
                row {
                    cell("ID")
                    cell("Name")
                    cell("Description") { align = TextAlign.CENTER }
                    cell("Completed")
                }
            }

            body {
                tasks.forEach { task ->
                    row {
                        cell(task.id.toString())
                        cell(task.name)
                        cell(task.description.joinToString(", ")) { align = TextAlign.CENTER }
                        cell(if (task.done) "Yes" else "No")
                    }
                }
            }
        }

        Terminal().println(table)
    }
}