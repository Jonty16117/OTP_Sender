package com.example.otp_sender.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.otp_sender.R
import com.example.otp_sender.datastuff.Contact


class ContactsAdapter(private val context: Context, private val contactForViewList: List<Contact>) : BaseAdapter() {
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var contactNum: TextView
    override fun getCount(): Int {
        return contactForViewList.size
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
            currentview = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false)
        }
        firstName = currentview!!.findViewById(R.id.firstName)
        lastName = currentview.findViewById(R.id.lastName)
        contactNum = currentview.findViewById(R.id.contactNo)
        firstName.text = contactForViewList[position].firstName
        lastName.text = contactForViewList[position].lastName
        contactNum.text = contactForViewList[position].contactNo
        return currentview
    }
}