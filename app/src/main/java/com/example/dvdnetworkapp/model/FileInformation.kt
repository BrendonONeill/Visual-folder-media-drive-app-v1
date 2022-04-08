package com.example.dvdnetworkapp.model

data class FileInformation(
    val contentId: Int,
    val contentName: String,
    val contentImage: String,
    val episodes: Int,
    val folder: String,
    val fileName: String,
    val fileExtention: String
    )
