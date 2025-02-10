package com.example.marketplaceapp

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: String,
    val description: String,
    val sellerName: String,
    val sellerPhone: String
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
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(price)
        parcel.writeString(description)
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
}