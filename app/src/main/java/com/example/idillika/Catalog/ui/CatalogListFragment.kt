package com.example.idillika.Catalog.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.idillika.App
import com.example.idillika.Catalog.Data.repository.CatalogListRepositoryImpl
import com.example.idillika.databinding.FragmentCatalogBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
class CatalogListFragment : Fragment() {

    private val viewModel = CatalogListViewModel(
        repository = CatalogListRepositoryImpl(
            restApi = App.restApi
        )
    )
    private lateinit var binding: FragmentCatalogBinding
    private lateinit var adapter: CatalogListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = CatalogListAdapter { entity ->

        }
        binding.RecyclerView.adapter = adapter

        viewModel.productsFlow
            .onEach { list -> adapter.submitList(list) }
            .launchIn(lifecycleScope)
    }


}




