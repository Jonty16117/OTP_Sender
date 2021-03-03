package com.example.otp_sender.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.otp_sender.model.DataModel

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(dataModel: DataModel)

    @Query("SELECT * FROM Contacts_Information")
    fun getHistory() : LiveData<DataModel>

}