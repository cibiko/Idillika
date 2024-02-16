package com.example.idillika

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idillika.Catalog.ui.ProductAdapter
import kotlinx.coroutines.launch


class Basket_Activity: AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_screen)


        val btn = findViewById<ImageView>(R.id.nazadImageView)
        val btn2 = findViewById<Button>(R.id.appCompatButton)
        val recyclerViewFavorites = findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        val korzina = findViewById<ImageView>(R.id.imageView)
        val TW = findViewById<TextView>(R.id.TW)


        lifecycleScope.launch {
            val favoriteProducts = FavoritesManager.getAllFavoriteProducts(this@Basket_Activity)
            if (favoriteProducts.isNotEmpty()) {
                productAdapter = ProductAdapter(favoriteProducts)
                recyclerViewFavorites.adapter = productAdapter
                recyclerViewFavorites.layoutManager = LinearLayoutManager(this@Basket_Activity)
            }



            if (favoriteProducts.isEmpty()) {
                TW.visibility = View.VISIBLE
                korzina.visibility = View.VISIBLE
                btn2.visibility = View.VISIBLE
            } else {
                TW.visibility = View.GONE
                korzina.visibility = View.GONE
                btn2.visibility = View.GONE
            }
        }



        btn2.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }
}
