package com.example.otp_sender.datastuff

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao() : ContactsDao

    companion object {
        fun getDatabaseInstance(context: Context): ContactsDatabase {
            return Room.databaseBuilder(context, ContactsDatabase::class.java, "contact-history-database").build()

        }
    }
}