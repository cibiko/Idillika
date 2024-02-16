package com.example.idillika.Catalog.Domain.repository

import com.example.idillika.Catalog.Domain.entity.CatalogEntity

interface CatalogListRepository {
    suspend fun getListCatalog(): List<CatalogEntity>

}

