package com.example.otp_sender.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.otp_sender.R
import com.example.otp_sender.datastuff.Contact
import org.json.JSONObject


class HomeFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(
                R.layout.fragment_home, container, false
        )

        //showing contacts in home tab
        val listView: ListView = currentView.findViewById(R.id.contact_list)
        val contactList = mutableListOf<Contact>()

        val jsonString = requireContext().assets.open("contactsList.json")
                .bufferedReader().use { it.readText() }

        //getting data from json string
        val obj = JSONObject(jsonString)
        val allContacts: JSONObject = obj.getJSONObject("contacts")

        for (i in 0..10) {
            contactList.add(Contact(
                    allContacts.getJSONObject(i.toString()).getString("firstName"),
                    allContacts.getJSONObject(i.toString()).getString("lastName"),
                    allContacts.getJSONObject(i.toString()).getString("contactNo"),
                    "No OTP Sent")
            )
        }

        listView.adapter = ContactsAdapter(requireContext(), contactList)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> // value of item that is clicked
            // Toast the values
            Toast.makeText(context, "Index :${position} selected", Toast.LENGTH_LONG).show()

            // Go to next acitivity
            val intent = Intent(context, ShowContactActivity::class.java).apply {
                val firstName = allContacts.getJSONObject(position.toString()).getString("firstName")
                val lastName = allContacts.getJSONObject(position.toString()).getString("lastName")
                val contactNo = allContacts.getJSONObject(position.toString()).getString("contactNo")

                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("contactNo", contactNo)
            }
            requireContext().startActivity(intent)
        }

        return currentView
    }
}
