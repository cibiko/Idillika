package com.example.idillika.Catalog.Domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogEntity(
    val id: Int,
    val imageLink: String,
    val title: String,
    val sort: String,
    var available: Boolean = false,
    val price: List<String>,
) : Parcelable

