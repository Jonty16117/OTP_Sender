package com.example.otp_sender.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.otp_sender.R
import com.example.otp_sender.datastuff.Contact
import com.example.otp_sender.datastuff.ContactEntry


class HistoryAdapter(private val context: Context, private val contactsForViewList: List<ContactEntry>) : BaseAdapter() {

    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var contactNum: TextView
    private lateinit var otp: TextView
    private lateinit var messageId: TextView

    override fun getCount(): Int {
        return contactsForViewList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var currentview = convertView
        if (currentview == null) {
            currentview = LayoutInflater.from(context).inflate(R.layout.history_row, parent, false)
        }
        firstName = currentview!!.findViewById(R.id.firstName)
        lastName = currentview.findViewById(R.id.lastName)
        contactNum = currentview.findViewById(R.id.contactNo)
        otp = currentview.findViewById(R.id.otp)
        messageId = currentview.findViewById(R.id.messageId)

        firstName.text = contactsForViewList[position].firstName
        lastName.text = contactsForViewList[position].lastName
        contactNum.text = contactsForViewList[position].contactNo
        otp.text = "OTP Sent: ${contactsForViewList[position].otp}"
        messageId.text = "Message ID: ${contactsForViewList[position].messageId}"

        return currentview
    }
}