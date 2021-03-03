package com.example.otp_sender.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactsInformation")
data class Contact(

        @ColumnInfo(name = "firstName")
        val firstName: String,

        @ColumnInfo(name = "lastName")
        val lastName: String,

        @ColumnInfo(name = "contactNo")
        var contactNo: String,

        @ColumnInfo(name = "otp")
        var otp: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}