package com.example.marketplaceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: ArrayList<Product>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        productList = ArrayList()
        productAdapter = ProductAdapter(this, productList)
        recyclerViewProducts.adapter = productAdapter

        database = FirebaseDatabase.getInstance().reference.child("products")

        


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (postSnapshot in snapshot.children) {
                    val product = postSnapshot.getValue(Product::class.java)
                    if (product != null) {
                        productList.add(product)
                    }
                }
                productAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        }

        )
    }
}