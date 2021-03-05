package com.example.otp_sender.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.otp_sender.R
import com.example.otp_sender.viewmodel.MainViewModel


class ShowContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)
    }

    private val ACCOUNT_SID = "ACbaf453bb08a10e6226758d98a1decada"
    private val AUTH_TOKEN = "27f77074c08310fe9a78bffe8e514876"
    private val SERVER_NO = "5596488509"
    private val intnt = Intent()

    val vmObj = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

    fun sendotp(view: View) {

        vmObj.sendMessage(
            ACCOUNT_SID,
            AUTH_TOKEN,
            intnt.getStringExtra("contactNo")!!,
            SERVER_NO,
            this,
            intnt.getStringExtra("firstName")!!,
            intnt.getStringExtra("lastName")!!
        )
    }
}