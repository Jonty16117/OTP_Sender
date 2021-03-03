package com.example.otp_sender.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.otp_sender.R

class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
                R.layout.fragment_home, container, false
        )

        //showing contacts in home tab
        var contactsArray = mutableListOf<String>("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")
        val listView: ListView = view.findViewById(R.id.contacts_list)
        val adapter = activity?.let {
            ArrayAdapter<String>(
                    it,
                    R.layout.fragment_home,
                    contactsArray
            )
        }

        val context = context as MainActivity

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> // value of item that is clicked
            val itemValue = listView.getItemAtPosition(position) as String

            // Toast the values
            Toast.makeText(activity,"Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG).show()
        }
        return view
    }
}
