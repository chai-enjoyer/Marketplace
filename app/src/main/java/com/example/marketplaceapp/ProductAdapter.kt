package com.example.marketplaceapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private val context: Context, private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        Glide.with(context).load(product.imageUrl as String).into(holder.imageViewProduct)
        holder.textViewProductName.text = product.name
        holder.textViewProductPrice.text = "$${product.price}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT", product)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = productList.size
}