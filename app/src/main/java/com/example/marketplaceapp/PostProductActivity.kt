package com.example.marketplaceapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplaceapp.databinding.ActivityPostProductBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference

class PostProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostProductBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().reference

        val submitButton = findViewById<Button>(R.id.btnSubmitProduct)
        submitButton.setOnClickListener {
            val productName = binding.etProductName.text.toString()
            val productPrice = binding.etProductPrice.text.toString()
            val productDescription = binding.etProductDescription.text.toString()
            val sellerName = binding.etSellerName.text.toString()
            val sellerPhone = binding.etSellerPhone.text.toString()
            val imageUrl = binding.etImageUrl.text.toString()

            if (!validateInputs(productName, productPrice, productDescription, imageUrl, sellerName, sellerPhone)) {
                return@setOnClickListener
            }

            val product = Product( productName, imageUrl, productPrice, productDescription, sellerName, sellerPhone)

            val newProductKey = database.child("products").push().key

            if (newProductKey != null) {
                val productValues = product.toMap()
                val childUpdates = hashMapOf<String, Any>(
                    "/products/$newProductKey" to productValues
                )
                database.updateChildren(childUpdates)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Product posted successfully!", Toast.LENGTH_SHORT).show()
                        clearFormFields()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to post product", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Error generating product key", Toast.LENGTH_SHORT).show()
            }
        }

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateInputs(
        productName: String,
        productPriceStr: String,
        productDescription: String,
        imageUrl: String,
        sellerName: String,
        sellerPhone: String
    ): Boolean {
        if (productName.isEmpty() || productPriceStr.isEmpty() || productDescription.isEmpty() || imageUrl.isEmpty() || sellerName.isEmpty() || sellerPhone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun clearFormFields() {
        binding.etProductName.text.clear()
        binding.etProductPrice.text.clear()
        binding.etProductDescription.text.clear()
        binding.etImageUrl.text.clear()
        binding.etSellerName.text.clear()
        binding.etSellerPhone.text.clear()
    }
}