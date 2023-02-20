package com.applemango.memodemo.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_memo")
data class MemoData(
        var title: String,
        val content: String,
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
):Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(content)
                parcel.writeInt(id)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<MemoData> {
                override fun createFromParcel(parcel: Parcel): MemoData {
                        return MemoData(parcel)
                }

                override fun newArray(size: Int): Array<MemoData?> {
                        return arrayOfNulls(size)
                }
        }
}