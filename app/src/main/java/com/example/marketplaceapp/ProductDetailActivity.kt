package com.example.marketplaceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.getParcelableExtra<Product>("PRODUCT")!!

        val imageViewProductDetail: ImageView = findViewById(R.id.imageViewProductDetail)
        val textViewProductNameDetail: TextView = findViewById(R.id.textViewProductNameDetail)
        val textViewProductDescription: TextView = findViewById(R.id.textViewProductDescription)
        val textViewSellerName: TextView = findViewById(R.id.textViewSellerName)
        val textViewSellerPhone: TextView = findViewById(R.id.textViewSellerPhone)
        val buttonBackToMain: Button = findViewById(R.id.buttonBackToMain)

        Glide.with(this).load(product.imageUrl).into(imageViewProductDetail)
        textViewProductNameDetail.text = product.name
        textViewProductDescription.text = product.description
        textViewSellerName.text = "Seller: ${product.sellerName}"
        textViewSellerPhone.text = "Phone: ${product.sellerPhone}"

        buttonBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}