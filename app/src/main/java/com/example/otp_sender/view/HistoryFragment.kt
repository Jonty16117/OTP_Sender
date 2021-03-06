package com.example.otp_sender.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.otp_sender.R
import com.example.otp_sender.datastuff.ContactEntry
import com.example.otp_sender.viewmodel.MainViewModel
import com.example.otp_sender.viewmodel.MainViewModelFactory

@Suppress("JoinDeclarationAndAssignment", "SpellCheckingInspection")
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val currentView = inflater.inflate(
            R.layout.fragment_history, container, false
        )

        val context = requireContext()

        val viewModelFactory = MainViewModelFactory(context)
        val vmObj = ViewModelProvider(requireActivity(),viewModelFactory)
                .get(MainViewModel::class.java)

        println("viewmodel created")
        val listView: ListView = currentView.findViewById(R.id.history_list)

        println("after viewmodel is created")
        //creating observer which update the contact list after a new entry is added
        val contactlistobserver = Observer<List<ContactEntry>> { newlist ->

//            val contactList = mutableListOf<Contact>()
//            if(newlist==null) {
//                println("newlist is null")
//                contactList.add(
//                        Contact(" ", "", "", " ")
//                )
//            }
//            for (i in newlist) {
//                contactList.add(
//                    Contact(i.firstName, i.lastName, i.contactNo, i.otp)
//                )
//            }
            listView.adapter = HistoryAdapter(context, newlist)
        }

//        adding observer to viewmodel
        vmObj.getlivehistorylistfromvm(context)!!.observe(requireActivity(), contactlistobserver)

        return currentView
    }
}