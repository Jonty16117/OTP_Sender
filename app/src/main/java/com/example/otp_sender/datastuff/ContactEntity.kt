package com.example.otp_sender.datastuff

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactsInformation")
data class ContactEntity(

        @ColumnInfo(name = "firstname")
        val firstName: String,

        @ColumnInfo(name = "lastname")
        val lastName: String,

        @ColumnInfo(name = "contactno")
        var contactNo: String,

        @ColumnInfo(name = "otP")
        var otp: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var iD: Int? = null

}