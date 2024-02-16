package com.example.idillika

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import com.example.idillika.Catalog.ui.CatalogListFragment
import com.example.idillika.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {

    private val viewBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)



        val btn = findViewById<ImageView>(R.id.Korzina)
        val btn2 = findViewById<ImageView>(R.id.nazadImageView)


        btn.setOnClickListener {
            val intent = Intent(this, Basket_Activity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this, First_screen_activity::class.java)
            startActivity(intent)
        }






        showProductsListFragment()

    }

    private fun showProductsListFragment(){

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.add(
            viewBinding.fragmentContainer.id,
            CatalogListFragment())


            fragmentTransaction.commit()
    }



}




