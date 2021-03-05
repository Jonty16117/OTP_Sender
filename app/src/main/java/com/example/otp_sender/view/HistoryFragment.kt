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
import com.example.otp_sender.datastuff.Contact
import com.example.otp_sender.datastuff.ContactEntity
import com.example.otp_sender.viewmodel.MainViewModel

@Suppress("JoinDeclarationAndAssignment", "SpellCheckingInspection")
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val currentView = inflater.inflate(
            R.layout.fragment_history, container, false
        )

        print("before viewmodel is created")
        var vmObj = ViewModelProvider(
            this,ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        val listView: ListView = currentView.findViewById(R.id.history_list)

        println("after viewmodel is created")
        //creating observer which update the contact list after a new entry is added
        val contactlistobserver = Observer<List<ContactEntity>> { newlist ->

            val contactList = mutableListOf<Contact>()
            if(newlist==null)
                println("newlist is null")
            for (i in newlist) {
                contactList.add(
                    Contact(
                        i.firstName,
                        i.lastName,
                        i.contactNo,
                        i.otp
                    )
                )
            }
            listView.adapter = ContactsAdapter(requireContext(), contactList)
        }

        //adding observer to viewmodel
        vmObj.getHistory().observe(viewLifecycleOwner, contactlistobserver)

        return currentView
    }
}



//class HistoryFragment : Fragment() {
//
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//
////        val view = inflater.inflate(
////            R.layout.fragment_history, container, false
//        )
//
//        //lateinit var viewModelFactory: MainViewModelFactory
//        //var viewModel: MainViewModel
//
//        var viewmodel = ViewModelProvider(this,
//            ViewModelProvider.NewInstanceFactory())
//            .get(MainViewModel::class.java)
//
//
//        //showing history in history tab
//        val listView: ListView = view.findViewById(R.id.history_list)
//
//        val context = context
//        var livedata = viewmodel.getHistory(requireContext()).observe(viewLifecycleOwner, Observer { List<ContactEntity> })
//
//        var history = mutableListOf<Contact>()
//
//        if (livedata != null) {
//            for(i in livedata) {
//                var newContact = Contact(
//                        i.firstName,
//                        i.lastName,
//                        i.contactNo,
//                        i.otp)
//                history.add(newContact)
//            }
//        }
//
//        listView.adapter = ContactsAdapter(context!!, history)
//
//        return view
//    }
//}

//custom viewmodel factory
//class MainViewModelFactory(): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            return MainViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown Viewmodel class")
//    }
//}
