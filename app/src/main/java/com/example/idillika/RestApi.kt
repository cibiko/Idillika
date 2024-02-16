package com.example.idillika

import com.example.idillika.Catalog.Data.dto.CatalogDto
import retrofit2.http.GET

interface RestApi {

    @GET("https://idillika.com/api/catalogList.php?filter=%7D&page=1&page_size=10&section=21&sort=ASC&session_id=7a8c3face1a551636c3f45ef50349ff9")
    suspend fun getProductsList(): List<CatalogDto>

}