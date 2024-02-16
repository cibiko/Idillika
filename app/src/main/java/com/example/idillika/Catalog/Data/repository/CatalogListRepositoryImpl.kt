package com.example.idillika.Catalog.Data.repository

import com.example.idillika.Catalog.Domain.entity.CatalogEntity
import com.example.idillika.Catalog.Domain.repository.CatalogListRepository
import com.example.idillika.RestApi

class CatalogListRepositoryImpl(
    private val restApi: RestApi,

):CatalogListRepository {


    override suspend fun getListCatalog(): List<CatalogEntity> {
        return restApi.getProductsList().map { CatalogDto ->
            CatalogEntity(
                id = CatalogDto.id,
                imageLink = CatalogDto.imageLink,
                title = CatalogDto.title,
                sort = CatalogDto.sort,
                available = CatalogDto.available,
                price = CatalogDto.price,


                )
        }
    }


}





