package com.example.otp_sender.datastuff

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactEntriesTableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contactEntry: ContactEntry)

    @Query("SELECT * FROM ContactEntry")
    fun getHistory() : LiveData<List<ContactEntry>>

    @Query("DELETE FROM ContactEntry")
    fun clear()
}