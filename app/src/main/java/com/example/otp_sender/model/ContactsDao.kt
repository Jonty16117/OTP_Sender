package com.example.otp_sender.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM ContactsInformation")
    fun getHistory() : LiveData<List<Contact>>?

    @Query("DELETE FROM ContactsInformation")
    fun clear()
}