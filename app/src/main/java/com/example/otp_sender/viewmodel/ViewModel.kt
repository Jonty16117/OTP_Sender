package com.example.otp_sender.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otp_sender.model.DataModel
import com.example.otp_sender.model.Repository

class MainViewModel : ViewModel() {

    var liveData: LiveData<DataModel>? = null

    fun insertData(
        context: Context, 
        firstName: String, 
        lastName: String,
        contactNo: Int,
        otp: Int) {

       Repository.insertData(
        context: Context, 
        firstName: String, 
        lastName: String,
        contactNo: Int,
        otp: Int)
    }

    fun getHistory(context: Context) : LiveData<DataModel>? {
        liveData = Repository.getHistory(context)
        return liveData
    }

}