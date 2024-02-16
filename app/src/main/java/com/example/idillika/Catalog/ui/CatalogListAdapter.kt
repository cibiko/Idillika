package com.example.idillika.Catalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.idillika.Catalog.Domain.entity.CatalogEntity
import com.example.idillika.R
import com.example.idillika.databinding.ItemCatalogBinding


class CatalogListAdapter(
    private val onClick: (entity: CatalogEntity) -> Unit
) : ListAdapter<CatalogEntity, CatalogListAdapter.CatalogViewHolder>(CatalogDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CatalogViewHolder(private val itemBinding: ItemCatalogBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                val catalogEntity = currentList[adapterPosition]
                onClick(catalogEntity)
            }

            itemBinding.heartImageView.setOnClickListener {
                val catalogEntity = currentList[adapterPosition]
                catalogEntity.available = !catalogEntity.available
                if (catalogEntity.available) {
                    FavoritesManager.addFavorite(itemBinding.root.context, catalogEntity.id)
                    FavoritesManager.setHeartEnabled(itemBinding.root.context, catalogEntity.id.toString(), true)
                } else {
                    FavoritesManager.removeFavorite(itemBinding.root.context, catalogEntity.id)
                    FavoritesManager.setHeartEnabled(itemBinding.root.context, catalogEntity.id.toString(), false)
                }
                notifyItemChanged(adapterPosition)
            }



    }

    fun bind(entity: CatalogEntity) {
            with(itemBinding) {
                    logoImageView.load(entity.imageLink)
                    textViewTitle.text = entity.title
                    priceTextView.text = entity.price[0]

                val isHeartEnabled = FavoritesManager.isHeartEnabled(root.context, entity.id.toString())
                heartImageView.setImageResource(
                    if (isHeartEnabled) R.drawable.heart_active
                    else R.drawable.heart_inactive
                )
            }
        }
    }




    class CatalogDiffUtil : DiffUtil.ItemCallback<CatalogEntity>() {

        override fun areItemsTheSame(
            oldItem: CatalogEntity,
            newItem: CatalogEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CatalogEntity,
            newItem: CatalogEntity
        ): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.imageLink == newItem.imageLink
                    && oldItem.title == newItem.title
                    && oldItem.price == newItem.price
                    && oldItem.available == newItem.available
        }
    }
}





