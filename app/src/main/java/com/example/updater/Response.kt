package com.example.updater

data class Response(
    val changelog_device: String,
    val changelog_misc: String,
    val changelog_securitypatch: String,
    val changelog_settings: String,
    val changelog_system: String,
    val datetime: Int,
    val filename: String,
    val id: String,
    val romtype: String,
    val size: Int,
    val url: String, // this is what i want to download
    val version: String
)