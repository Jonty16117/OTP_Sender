package com.example.otp_sender.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.otp_sender.model.DataModel
import com.example.otp_sender.model.Room.Database
import com.example.otp_sender.model.Retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Base64

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

        fun sendMessage(
            ACCOUNT_SID: String,
            AUTH_TOKEN: String,
            body: String,
            to: String, 
            from: String, 
            context: Context,
            callback: Callback): TwilioResponse {
         
            val twilio: Twilio = Twilio.create(context, ACCOUNT_SID, AUTH_TOKEN)
            var message: TwilioMessage  = TwilioMessage.create(to, from, body, callback)
            val response: TwilioResponse = twilio.send(message)
            
            return response
        }

    }
}