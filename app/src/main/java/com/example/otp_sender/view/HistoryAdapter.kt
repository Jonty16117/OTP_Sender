package com.example.otp_sender.view

import android.annotation.SuppressLint
import com.example.otp_sender.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.example.otp_sender.datastuff.Contact
import com.example.otp_sender.datastuff.ContactEntity


class HistoryAdapter(private val context: Context, private val contactsForViewList: List<Contact>?) : BaseAdapter() {

    //history is now a list of Contacts object
    var history = contactsForViewList

    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var contactNum: TextView
    private lateinit var otp: TextView


    override fun getCount(): Int {
        return history!!.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var currentview = convertView
        if (currentview == null) {
            currentview = LayoutInflater.from(context).inflate(R.layout.row, parent, false)
        }
        firstName = currentview!!.findViewById(R.id.firstName)
        lastName = currentview.findViewById(R.id.lastName)
        contactNum = currentview.findViewById(R.id.contactNo)
        otp = currentview.findViewById(R.id.otp)

        firstName.text = history!![position].firstName
        lastName.text = history!![position].lastName
        contactNum.text = history!![position].contactNo
        otp.text = "123456"
        
        return currentview
    }
}