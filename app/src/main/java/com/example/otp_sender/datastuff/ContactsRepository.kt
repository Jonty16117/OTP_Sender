package com.example.otp_sender.datastuff

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hihi.twiliosms.Twilio
import com.hihi.twiliosms.TwilioMessage
import com.hihi.twiliosms.TwilioResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.concurrent.Callable

@Suppress("JoinDeclarationAndAssignment", "SpellCheckingInspection")
class ContactsRepository(context : Application) {

    //        declaring the contact list livedata
    private lateinit var contactsDatabase: ContactsDatabase

    private val c = context
    //        returns live data of contactlist
    var historylist: LiveData<List<ContactEntity>> get() = getHistoryFromDB(c)
    //        setting a list of 1 contact in the list of live data
    init {
//        val contact = ContactEntity("firstname","lastname","phoneno","otp")
//            val contact = ContactEntity("","","","")
        historylist = getHistoryFromDB(context)
    }

    private fun initializeDB(context: Context) : ContactsDatabase {
        return ContactsDatabase.getDatabaseInstance(context)
    }

    fun insertContact(
        context: Context,
        firstName: String,
        lastName: String,
        contactNo: String,
        otp: String) {

        contactsDatabase = initializeDB(context)

        CoroutineScope(IO).launch {
            val rowData = ContactEntity(firstName, lastName, contactNo, otp)
            contactsDatabase.contactsDao().insertContact(rowData)
        }

        //fetch fresh data
        historylist = getHistoryFromDB(context) as MutableLiveData<List<ContactEntity>>
    }



    private fun getHistoryFromDB(context: Context) : LiveData<List<ContactEntity>> {
        return initializeDB(context).contactsDao().getHistory()
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
    ): Callable<TwilioResponse>? {

        val twilio: Twilio = Twilio.create(context, ACCOUNT_SID, AUTH_TOKEN)
        val message: TwilioMessage = TwilioMessage.create(to, from, body, null)

        return twilio.send(message)
    }
}