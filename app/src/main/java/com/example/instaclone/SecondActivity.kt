package com.example.instaclone

import android.annotation.SuppressLint
//import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.PickContact()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                val data: Intent? = result.data
                if (result != null) {
                    val contactName = retrieveContactName(result)
                    if (contactName.isNotEmpty()) {
                        val text = findViewById<TextView>(R.id.textView8)
                        text.text = contactName
                    }
                    val companyName = retrieveContactPhoneNumber(result)
                    if(companyName.isNotEmpty()){
                        val text = findViewById<TextView>(R.id.textView9)
                        text.text = companyName
                    }
                    else{
                        val text = findViewById<TextView>(R.id.textView9)
                        text.text = "Unemployed"
                    }
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        val intent = Intent(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI)
        val contactLauncherButton = findViewById<Button>(R.id.button2)
        contactLauncherButton.setOnClickListener{resultLauncher.launch(null)}
//        resultLauncher.launch(intent)

    }


    @SuppressLint("Range")
    private fun retrieveContactName(contactUri: android.net.Uri?): String {
        contactUri ?: return ""

        val cursor = contentResolver.query(contactUri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                return it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
            }
        }
        return ""
    }

    @SuppressLint("Range")
    private fun retrieveContactPhoneNumber(contactUri: android.net.Uri?): String {
        contactUri ?: return ""

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(contactUri.lastPathSegment),
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val phoneNumber = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                return phoneNumber ?: ""
            }
        }
        return ""
    }




}