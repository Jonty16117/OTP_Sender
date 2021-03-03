package com.example.otp_sender.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.otp_sender.model.DataModel
import com.example.otp_sender.model.Room.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository {

    companion object {

        var database: Database? = null

        var dataModel: LiveData<DataModel>? = null

        fun initializeDB(context: Context) : Database {
            return Database.getDataseClient(context)
        }

        fun insertData(
            context: Context, 
            firstName: String, 
            lastName: String,
            contactNo: Int,
            otp: Int) {

            database = initializeDB(context)

            CoroutineScope(IO).launch {
                val rowData = DataModel(firstName, lastName, contactNo, otp)
                database!!.DAO().InsertData(rowData)
            }

        }

        fun getHistory(context: Context) : LiveData<DataModel>? {

            database = initializeDB(context)

            dataModel = database!!.DAO().getHistory()

            return dataModel
        }

    }
}