package com.example.otp_sender.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.otp_sender.datastuff.ContactEntity
import com.example.otp_sender.datastuff.ContactsRepository
import kotlin.random.Random

//@SuppressLint("StaticFieldLeak")
class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val contactsRepository = ContactsRepository(application)

//    val historylist: LiveData<List<ContactEntity>>
//        get() = contactsRepository.historylist

    //    lateinit var liveData: LiveData<List<ContactEntity>>
    public fun getHistory() : LiveData<List<ContactEntity>> {
        return contactsRepository.historylist
    }

    public fun sendMessage(
            ACCOUNT_SID: String,
            AUTH_TOKEN: String,
            to: String,
            from: String,
            context: Context,
            firstName: String,
            lastName: String): String {

        val otp = listOf(
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9)).joinToString("")
        val body = "Hi. Your OTP is: $otp"

        val responseFromSmsApi = contactsRepository.sendRequestToSmsApi(
                ACCOUNT_SID, AUTH_TOKEN, body, to, from, context)

        //if message sent successfully then update the local database
        if (true){
            contactsRepository.insertContact(context, firstName, lastName, to, otp)
            return "Message Sent"
        }
        else {
            return "Failed To Send Message"
        }
    }
}