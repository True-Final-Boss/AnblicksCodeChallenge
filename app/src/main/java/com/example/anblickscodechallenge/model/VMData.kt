package com.example.anblickscodechallenge.model

sealed class VMData {
    data class Response(val response: ChannelResponse) : VMData()
    data class Error(val error: String) : VMData()
}