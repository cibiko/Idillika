package com.example.idillika.Catalog.ui

import android.accounts.NetworkErrorException
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idillika.Catalog.Domain.entity.CatalogEntity
import com.example.idillika.Catalog.Domain.repository.CatalogListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException


class CatalogListViewModel(
    private val repository: CatalogListRepository
): ViewModel() {

    init {
        getListProduct()


    }

    val productsFlow = MutableStateFlow<List<CatalogEntity>?>(null)


    private fun getListProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = repository.getListCatalog()
                productsFlow.emit(list)
            } catch (e: Exception) {
                handleApiError(e)
            }
        }
    }
    private fun handleApiError(e: Exception) {
        when (e) {
            is NetworkErrorException -> {
                Log.e("TAG", "Ошибка связи с сервером: ${e.message}", e)
            }

            is TimeoutException -> {
                Log.e("TAG", "Превышено время ожидания ответа от сервера: ${e.message}", e)
            }

            else -> {
                Log.e("TAG", "Не удалось получить список продуктов: ${e.message}", e)
            }
        }
    }





}








