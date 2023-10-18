package com.example.phillipinetouristaide

import android.os.Parcel
import android.os.Parcelable

data class ImageInfo(val imageResourceId1: Int, val imageResourceId2: Int, val imageResourceId3: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResourceId1)
        parcel.writeInt(imageResourceId2)
        parcel.writeInt(imageResourceId3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageInfo> {
        override fun createFromParcel(parcel: Parcel): ImageInfo {
            return ImageInfo(parcel)
        }

        override fun newArray(size: Int): Array<ImageInfo?> {
            return arrayOfNulls(size)
        }
    }

    //Function for storing more than 1 image from MainActivity
    fun getImageResourceId(position: Int): Int {
        return when (position) {
            0 -> imageResourceId1
            1 -> imageResourceId2
            2 -> imageResourceId3
            else -> R.drawable.bataneslaoagvigan
        }
    }
}