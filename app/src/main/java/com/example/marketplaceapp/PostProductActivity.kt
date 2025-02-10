// PostProductActivity.kt
package com.example.marketplaceapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_post_product.*

class PostProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_product)

        // Handle form submission
        btnSubmitProduct.setOnClickListener {
            val productName = etProductName.text.toString().trim()
            val productPriceStr = etProductPrice.text.toString().trim()
            val productDescription = etProductDescription.text.toString().trim()

            if (productName.isEmpty() || productPriceStr.isEmpty() || productDescription.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val productPrice = try {
                productPriceStr.toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (productPrice <= 0) {
                Toast.makeText(this, "Price must be greater than zero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save the product to a database or send to server
            // Example: saveProductToDatabase(productName, productPrice, productDescription)

            Toast.makeText(this, "Product Posted!", Toast.LENGTH_SHORT).show()

            // Clear the form fields
            etProductName.text.clear()
            etProductPrice.text.clear()
            etProductDescription.text.clear()
        }
    }
}