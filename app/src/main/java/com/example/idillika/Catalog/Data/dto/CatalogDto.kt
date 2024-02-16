package com.example.idillika.Catalog.Data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    val id: Int,
    val imageLink: String,
    val title: String,
    val sort: String,
    var available: Boolean = false,
    val price: List<String>,


)

