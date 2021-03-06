package com.example.otp_sender.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.otp_sender.datastuff.ContactEntity
import com.example.otp_sender.datastuff.ContactsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    lateinit var context: Application

    @Before
    fun setup() {
        context =  androidx.test.core.app.ApplicationProvider.getApplicationContext()
    }

    @Test
    fun getlivehistorylistfromvm() {
        val contactsRepository = ContactsRepository(context)

        val livedata = contactsRepository.getlivehistorylistfromrepo()

        if (livedata == null)
            println("livedata is null")
        else
            println("livedata is not null")
    }
}