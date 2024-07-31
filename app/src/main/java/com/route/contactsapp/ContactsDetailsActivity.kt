package com.route.contactsapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactsDetailsActivity : AppCompatActivity() {
    lateinit var nameTextView: TextView
    lateinit var phoneTextView: TextView
    lateinit var descTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        nameTextView=findViewById(R.id.name)
        phoneTextView=findViewById(R.id.number)
        descTextView=findViewById(R.id.description)
        val name= intent.getStringExtra("name")
        val phone= intent.getStringExtra("phone")
        val description= intent.getStringExtra("description")
        nameTextView.text=name
        phoneTextView.text=phone
        descTextView.text=description
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}