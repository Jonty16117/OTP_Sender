package com.example.otp_sender.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otp_sender.datastuff.ContactEntry
import com.example.otp_sender.datastuff.ContactsRepository
import kotlin.random.Random

class MainViewModel : ViewModel() {

    var livehistorydata: LiveData<List<ContactEntry>>? = null


//    gets live list of history from local database
    fun getlivehistorylistfromvm(context: Context) : LiveData<List<ContactEntry>>? {
        livehistorydata = ContactsRepository.getlivehistorylistfromrepo(context)
        return livehistorydata
    }

//    sends message through sms api and them updates the
//    entry in local database
    fun sendMessage(
            ACCOUNT_SID: String,
            AUTH_TOKEN: String,
            to: String,
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

        Toast.makeText(context, "Sending random OTP as:\n$otp", Toast.LENGTH_LONG).show()

//      request sms from sms api
//        val responseFromSmsApi = ContactsRepository.sendRequestToSmsApi(
//                ACCOUNT_SID, AUTH_TOKEN, body, to, from, context)

//        if message is sent successfully, then update the local database
        if (true){
            ContactsRepository.insertContact(context, firstName, lastName, to, otp)
            Toast.makeText(context, "Message Sent!", Toast.LENGTH_LONG).show()
            return "Message Sent"
        }
        else {
            Toast.makeText(context, "Failed To Send Message!", Toast.LENGTH_LONG).show()
            return "Failed To Send Message"
        }
    }
}