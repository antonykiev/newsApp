package com.petproject.core.data

sealed interface Source {
    data class Data(
        val id: String?,
        val name: String
    ): Source

    data object Empty: Source
}

