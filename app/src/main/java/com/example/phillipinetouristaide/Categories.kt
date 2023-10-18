package com.example.phillipinetouristaide

import android.os.Parcel
import android.os.Parcelable

data class Categories(var dataImage:Int, var dataTitle:String, var dataText:String, var dataDesc: String, var data1:String, var data11:String, var data2:String, var data22:String, var data3:String, var data33:String, var data4:String, var data44:String, var data5:String, var data55:String, var data6:String, var data66:String, var data7:String, var data77:String, var data8:String, var data88:String, var data9:String, var data99:String, var data10:String, var data1010:String, var dataDetailImage: ImageInfo?): Parcelable {
    constructor(parcel: Parcel) : this(
        //Building and reading data from MainActivity
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(ImageInfo::class.java.classLoader)!!
    )

    //Collaborating the variables and the data from fetching data's in MainActivity
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImage)
        parcel.writeString(dataTitle)
        parcel.writeString(dataText)
        parcel.writeString(dataDesc)
        parcel.writeString(data1)
        parcel.writeString(data11)
        parcel.writeString(data2)
        parcel.writeString(data22)
        parcel.writeString(data3)
        parcel.writeString(data33)
        parcel.writeString(data4)
        parcel.writeString(data44)
        parcel.writeString(data5)
        parcel.writeString(data55)
        parcel.writeString(data6)
        parcel.writeString(data66)
        parcel.writeString(data7)
        parcel.writeString(data77)
        parcel.writeString(data8)
        parcel.writeString(data88)
        parcel.writeString(data9)
        parcel.writeString(data99)
        parcel.writeString(data10)
        parcel.writeString(data1010)
        parcel.writeParcelable(dataDetailImage, flags)
    }
    override fun describeContents(): Int {
        return 0
    }

    //Storing the data
    companion object CREATOR : Parcelable.Creator<Categories> {
        override fun createFromParcel(parcel: Parcel): Categories {
            return Categories(parcel)
        }

        //Array for size
        override fun newArray(size: Int): Array<Categories?> {
            return arrayOfNulls(size)
        }
    }
}
