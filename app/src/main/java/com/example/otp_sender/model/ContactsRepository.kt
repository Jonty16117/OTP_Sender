package com.example.otp_sender.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import com.example.otp_sender.model.ContactsDatabase.Companion.getDatabaseInstance
import com.hihi.twiliosms.Twilio
import com.hihi.twiliosms.TwilioMessage
import com.hihi.twiliosms.TwilioResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.concurrent.Callable
import javax.security.auth.callback.Callback

class ContactsRepository(private val database: RoomDatabase) {

    companion object {

        var contactsDatabase: ContactsDatabase? = null

        var contacts: LiveData<List<Contact>>? = null

        private fun initializeDB(context: Context) : ContactsDatabase? {
            return ContactsDatabase.getDatabaseInstance(context)
        }

        fun insertContact(
            context: Context,
            firstName: String,
            lastName: String,
            contactNo: String,
            otp: String) {

            contactsDatabase = getDatabaseInstance(context)

            CoroutineScope(IO).launch {
                val rowData = Contact(firstName, lastName, contactNo, otp)
                contactsDatabase!!.contactsDao().insertContact(rowData)
            }

        }



        fun getHistory(context: Context) : LiveData<List<Contact>>? {
            contactsDatabase = initializeDB(context)
            contacts = contactsDatabase!!.contactsDao().getHistory()
            return contacts
        }

//        fun getContacts(): LiveData<DataModel>? {
//
//        }

        fun sendRequestToSmsApi(
            ACCOUNT_SID: String,
            AUTH_TOKEN: String,
            body: String,
            to: String,
            from: String,
            context: Context,
            callback: Callback): Callable<TwilioResponse>? {

            val twilio: Twilio = Twilio.create(context, ACCOUNT_SID, AUTH_TOKEN)
            var message: TwilioMessage = TwilioMessage.create(to, from, body, callback.toString())

            return twilio.send(message)
        }

    }
}