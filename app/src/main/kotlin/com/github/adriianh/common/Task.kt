package com.github.adriianh.common

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val name: String,
    var description: MutableList<String> = mutableListOf(),
    var done: Boolean = false
)