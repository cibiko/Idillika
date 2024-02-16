package com.example.idillika.Catalog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.idillika.Catalog.Data.dto.CatalogDto
import com.example.idillika.R

class ProductAdapter(private val products: List<CatalogDto>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.TextViewTitle)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.logoImageView.load(product.imageLink)
        holder.nameTextView.text = product.title
        holder.titleTextView.text = product.title
        holder.priceTextView.text = product.price[0]
    }

    override fun getItemCount(): Int {
        return products.size
    }

}