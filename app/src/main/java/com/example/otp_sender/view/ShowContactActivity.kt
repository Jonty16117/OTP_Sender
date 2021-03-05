package com.example.otp_sender.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.otp_sender.R
import com.example.otp_sender.viewmodel.MainViewModel


class ShowContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)


        val ACCOUNT_SID = "ACbaf453bb08a10e6226758d98a1decada"
        val AUTH_TOKEN = "27f77074c08310fe9a78bffe8e514876"
        val SERVER_NO = "5596488509"
        //val intnt = Intent()

        //setting text values
        findViewById<TextView>(R.id.inputfirstname).text = intent.getStringExtra("firstName")
        findViewById<TextView>(R.id.inputlastname).text = intent.getStringExtra("lastName")
        findViewById<TextView>(R.id.inputcontactno).text = intent.getStringExtra("contactNo")

        val button = findViewById<Button>(R.id.send)
        fun onclick(){
            Toast.makeText(this, "Sending OTP", Toast.LENGTH_LONG).show()
            //Toast.makeText(this, "Sent", Toast.LENGTH_LONG).show()

        }
        button.setOnClickListener{onclick()}


//    val vmObj = ViewModelProvider(
//            this, ViewModelProvider.NewInstanceFactory())
//            .get(MainViewModel::class.java)
//
//    vmObj.sendMessage(
//    ACCOUNT_SID,
//    AUTH_TOKEN,
//    intnt.getStringExtra("contactNo")!!,
//    SERVER_NO,
//    this,
//    intnt.getStringExtra("firstName")!!,
//    intnt.getStringExtra("lastName")!!
//    )
    }
}