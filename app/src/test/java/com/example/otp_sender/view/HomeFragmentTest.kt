package com.example.otp_sender.view

import android.app.Instrumentation
import android.content.pm.InstrumentationInfo
import androidx.core.content.ContentProviderCompat.requireContext
import org.json.JSONObject
import org.junit.Test
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.file.Paths

class HomeFragmentTest {

    @Test
    fun testingFun() {

//        val ASSET_BASE_PATH = "../app/src/main/assets/"
//        val filename = "contactsList.json"
//        val currentAppPath = Paths.get("")
//                .toAbsolutePath().toString()
//        val filePath = Paths.get(currentAppPath,
//                "assets", "contactsList.json").toString()
//
//        val br = BufferedReader(InputStreamReader(FileInputStream(filePath)))
//        val sb = StringBuilder()
//        var line = br.readLine()
//        while (line != null) {
//            sb.append(line)
//            line = br.readLine()
//        }
//        val jsonString: String = sb.toString()

//        val jsonString = requireContext().assets.open("contactsList.json")
//                .bufferedReader().use { it.readText() }


//        val fileInString: String = requireContext()
//                .applicationContext.assets
//                .open("contactsList.json")
//                .bufferedReader()
//                .use {it.readText()}

//        val currentAppPath = Paths.get("")
//                .toAbsolutePath().toString()
//        val filePath = Paths.get(currentAppPath,
//                "src",
//                "main",
//                "java",
//                "com.example.otp_sender",
//                "view",
//                "contactsList.json").toString()
//        println("The path to be passed is: $filePath")
//        val bufferedReader: BufferedReader = File(filePath).bufferedReader()
//        val jsonString = bufferedReader.use{it.readText()}

        val obj = JSONObject("jsonString")
        val allContacts: JSONObject = obj.getJSONObject("contacts")

        for (i in 0..10) {
            println(allContacts.getJSONObject(i.toString()).getString("firstName"))
            println(allContacts.getJSONObject(i.toString()).getString("lastName"))
            println(allContacts.getJSONObject(i.toString()).getString("contactNo"))

        }

    }
}