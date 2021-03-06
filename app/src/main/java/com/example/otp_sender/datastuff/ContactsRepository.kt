package com.example.otp_sender.datastuff

import android.content.Context
import androidx.lifecycle.LiveData
import com.hihi.twiliosms.Twilio
import com.hihi.twiliosms.TwilioMessage
import com.hihi.twiliosms.TwilioResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.concurrent.Callable

@Suppress("JoinDeclarationAndAssignment", "SpellCheckingInspection")
class ContactsRepository {

    companion object {
        var historyDatabase: HistoryDatabase? = null
        var historylist: LiveData<List<ContactEntry>>? = null

        private fun initializeDB(context: Context) : HistoryDatabase {
            return HistoryDatabase.getDatabaseInstance(context)
        }

        fun insertContact(
                context: Context,
                firstName: String,
                lastName: String,
                contactNo: String,
                otp: String) {

            historyDatabase = initializeDB(context)

//        make request to Dao for inserting new contact entry
            CoroutineScope(IO).launch {
                val rowData = ContactEntry(firstName, lastName, contactNo, otp)
                historyDatabase!!.contactEntriesTableDao().insertContact(rowData)
            }

        }

        fun getlivehistorylistfromrepo(context: Context): LiveData<List<ContactEntry>> {
            historyDatabase = initializeDB(context)
            return historyDatabase!!.contactEntriesTableDao().getHistory()
        }

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
}
