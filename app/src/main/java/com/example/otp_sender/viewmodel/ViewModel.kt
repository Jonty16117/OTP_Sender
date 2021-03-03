package com.example.otp_sender.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otp_sender.model.Contact
import com.example.otp_sender.model.ContactsRepository
import javax.security.auth.callback.Callback
import kotlin.random.Random

class MainViewModel : ViewModel() {

    var liveData: LiveData<List<Contact>>? = null

    fun getHistory(context: Context) : LiveData<List<Contact>>? {
        liveData = ContactsRepository.getHistory(context)
        return liveData
    }

    fun sendMessage(
            ACCOUNT_SID: String,
            AUTH_TOKEN: String,
            to: String,
            from: String,
            context: Context,
            callback: Callback,
            firstName: String,
            lastName: String): String {

        var otp = listOf(
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9),
                Random.nextInt(0, 9)).joinToString("")
        val body = "Hi. Your OTP is: $otp"

        var responseFromSmsApi = ContactsRepository.sendRequestToSmsApi(
                ACCOUNT_SID, AUTH_TOKEN, body, to, from, context, callback)

        //if message sent successfully then update the local database
        if (true){
            ContactsRepository.insertContact(context, firstName, lastName, to, otp)
            return "Message Sent"
        }
        else {
            return "Failed To Send Message"
        }
    }
}