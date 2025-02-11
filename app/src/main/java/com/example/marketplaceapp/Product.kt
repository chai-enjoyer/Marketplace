package com.example.marketplaceapp

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val productId: String? = null,
    val productName: String? = null,
    val imageUrl: String? = null,
    val productPrice: String? = null,
    val productDescription: String? = null,
    val sellerName: String? = null,
    val sellerPhone: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(imageUrl)
        parcel.writeString(productPrice)
        parcel.writeString(productDescription)
        parcel.writeString(sellerName)
        parcel.writeString(sellerPhone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "productId" to productId,
            "productName" to productName,
            "imageUrl" to imageUrl,
            "productPrice" to productPrice,
            "productDescription" to productDescription,
            "sellerName" to sellerName,
            "sellerPhone" to sellerPhone
        )
    }
}