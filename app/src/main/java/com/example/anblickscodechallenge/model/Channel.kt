package com.example.anblickscodechallenge.model

import java.io.Serializable

data class Channel (
    val id: String,
    val title: String,
    val description: String,
    val dj: String,
    val djmail: String,
    val genre: String,
    val image: String,
    val largeimage: String? = null,
    val xlimage: String,
    val twitter: String,
    val updated: String,
    val listeners: String,
    val lastPlaying: String
): Serializable