package com.example.a4month1hw.ui.home

import java.io.Serializable

data class News(
    val id: Int,
    val title: String,
    val time: Long?
) : Serializable
