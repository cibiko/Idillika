package com.example.idillika.Catalog.Data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val imageLink: String,
    val title: String,
    val id: Int,
    val name: String,
    val price: List<String>
):Parcelable

