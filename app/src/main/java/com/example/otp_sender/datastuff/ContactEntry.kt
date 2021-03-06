package com.example.otp_sender.datastuff

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactEntry")
data class ContactEntry(

    @ColumnInfo(name = "firstName") val firstName: String,

    @ColumnInfo(name = "lastName") val lastName: String,

    @ColumnInfo(name = "contactNo") var contactNo: String,

    @ColumnInfo(name = "otp") var otp: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messageId")
    val messageId: Int? = null)