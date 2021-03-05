package com.example.otp_sender.datastuff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: ContactEntity)

    @Query("SELECT * FROM ContactsInformation")
    fun getHistory() : LiveData<List<ContactEntity>>

    @Query("DELETE FROM ContactsInformation")
    fun clear()
}